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
import kotlinx.coroutines.flow.flowOf
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
    private val currentYao = savedStateHandle.getStateFlow(
        key = YaoS,
        initialValue = YaoS_Default
    )



    val getYaoUIState: StateFlow<YaoBaseUiState> =
        currentYao.flatMapLatest { query ->
            flowOf(YaoBaseUiState(query))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = YaoBaseUiState(),
        )

    val getYaoExplainUiState: StateFlow<YaoExplainUiState> =
        searchQuery.flatMapLatest { query ->

            getYaoExplainUseCase(query)
                .asResult()
                .map { result ->
                    when (result) {
                        is Result.Success ->
                            if (YaoExplainUiState.Success(
                                    result.data.base,
                                    result.data.explain
                                ).isEmpty()
                            ) {
                                YaoExplainUiState.LoadFailed
                            } else {
                                YaoExplainUiState.Success(
                                    result.data.base,
                                    result.data.explain
                                )
                            }

                        is Result.Loading -> YaoExplainUiState.Loading
                        is Result.Error -> YaoExplainUiState.LoadFailed
                    }
                }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = YaoExplainUiState.Loading,
            )


    fun onSearchQueryChanged(query: Int) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onYaoChanged(index: Int){
        val oldYaos = currentYao.value
        val _YaoS = mutableListOf<Boolean>()
        oldYaos.forEachIndexed { _index, _b ->
            if (_index == index){
                _YaoS.add(!_b)
            }else{
                _YaoS.add(_b)
            }
        }
        savedStateHandle[YaoS] = _YaoS
    }
}


private const val SEARCH_MIN_FTS_ENTITY_COUNT = -1
private const val SEARCH_QUERY = "searchQuery"
private val YaoS_Default = listOf(true,true,true,true,true,true)
private const val YaoS = "YaoS"





