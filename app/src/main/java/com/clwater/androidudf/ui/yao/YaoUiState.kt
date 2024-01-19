package com.clwater.androidudf.ui.yao

sealed interface YaoUiState {
    data object Loading: YaoUiState
    data object LoadFailed: YaoUiState

    data class Success(
        val base: String = "",
        val explain: String = ""
    ): YaoUiState{
        fun isEmpty() = base.isEmpty() && explain.isEmpty()
    }
}