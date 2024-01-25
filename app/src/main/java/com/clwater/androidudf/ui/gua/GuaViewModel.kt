@file:OptIn(ExperimentalCoroutinesApi::class)

package com.clwater.androidudf.ui.gua

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clwater.androidudf.core.result.asResult
import com.clwater.androidudf.core.result.Result
import com.clwater.androidudf.data.repository.DefaultDatabaseRepository
import com.clwater.androidudf.domain.gua.GetGuaBaseUseCase
import com.clwater.androidudf.domain.gua.GetGuaExplainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GuaViewModel @Inject constructor(
    private val defaultDatabaseRepository: DefaultDatabaseRepository,
    getGuaExplainUseCase: GetGuaExplainUseCase,
    getGuaBaseUseCase: GetGuaBaseUseCase,
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



    val getYaoUIState: StateFlow<GuaBaseUiState> =
        currentYao.flatMapLatest { query ->
            getGuaBaseUseCase(query).map{ result ->
                GuaBaseUiState(name = result.name, yaos = currentYao.value)
            }

        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = GuaBaseUiState(),
        )

    val getGuaExplainUiState: StateFlow<GuaExplainUiState> =
        searchQuery.flatMapLatest { query ->

            getGuaExplainUseCase(query)
                .asResult()
                .map { result ->
                    when (result) {
                        is Result.Success ->
                            if (GuaExplainUiState.Success(
                                    result.data.base,
                                    result.data.explain
                                ).isEmpty()
                            ) {
                                GuaExplainUiState.LoadFailed
                            } else {
                                GuaExplainUiState.Success(
                                    result.data.base,
                                    result.data.explain
                                )
                            }

                        is Result.Loading -> GuaExplainUiState.Loading
                        is Result.Error -> GuaExplainUiState.LoadFailed
                    }
                }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = GuaExplainUiState.Loading,
            )

    fun initDatabase(){
        defaultDatabaseRepository.guaTableInit()
    }

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





