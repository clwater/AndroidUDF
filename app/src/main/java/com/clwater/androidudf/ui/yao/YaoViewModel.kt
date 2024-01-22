@file:OptIn(ExperimentalCoroutinesApi::class)

package com.clwater.androidudf.ui.yao

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clwater.androidudf.core.result.asResult
import com.clwater.androidudf.core.result.Result
import com.clwater.androidudf.domain.yao.GetYaoExplainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class YaoViewModel @Inject constructor(
    getYaoExplainUseCase: GetYaoExplainUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val searchQuery = savedStateHandle.getStateFlow(
        key = SEARCH_QUERY,
        initialValue = SEARCH_MIN_FTS_ENTITY_COUNT
    )
    val getYaoUiState: StateFlow<YaoUiState> =
        searchQuery.flatMapLatest { query ->

            getYaoExplainUseCase(query)
                .asResult()
                .map { result ->
                    when (result) {
                        is Result.Success ->
                            if (YaoUiState.Success(
                                    result.data.base,
                                    result.data.explain
                                ).isEmpty()
                            ) {
                                YaoUiState.LoadFailed
                            } else {
                                YaoUiState.Success(
                                    result.data.base,
                                    result.data.explain
                                )
                            }

                        is Result.Loading -> YaoUiState.Loading
                        is Result.Error -> YaoUiState.LoadFailed
                    }
                }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = YaoUiState.Loading,
            )


    fun onSearchQueryChanged(query: Int) {
        savedStateHandle[SEARCH_QUERY] = query
    }

}


private const val SEARCH_MIN_FTS_ENTITY_COUNT = -1
private const val SEARCH_QUERY = "searchQuery"
