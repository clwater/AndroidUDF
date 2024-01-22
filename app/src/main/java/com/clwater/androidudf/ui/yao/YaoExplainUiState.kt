package com.clwater.androidudf.ui.yao

sealed interface YaoExplainUiState {
    data object Loading: YaoExplainUiState
    data object LoadFailed: YaoExplainUiState

    data class Success(
        val base: String = "",
        val explain: String = ""
    ): YaoExplainUiState{
        fun isEmpty() = base.isEmpty() && explain.isEmpty()
    }
}