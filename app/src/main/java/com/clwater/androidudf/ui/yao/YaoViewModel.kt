package com.clwater.androidudf.ui.yao

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clwater.androidudf.domain.yao.GetYaoExplainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
): ViewModel() {
    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = SEARCH_MIN_FTS_ENTITY_COUNT)
    val getYaoUiState: StateFlow<YaoUiState> =
        searchQuery.flatMapLatest{query ->
            getYaoExplainUseCase(query)
                .map { result ->
                    if (result.base.isNotEmpty()){
                        YaoUiState.Success(result.base, result.explain)

                    } else{
                        YaoUiState.LoadFailed
                    }
                }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = YaoUiState.Loading,
            )

    fun onSearchQueryChanged(query: Int) {
        savedStateHandle[SEARCH_QUERY] = query
        Log.d("gzb", "query: $query")
    }

}


private const val SEARCH_MIN_FTS_ENTITY_COUNT = 1
private const val SEARCH_QUERY = "searchQuery"