package com.clwater.androidudf.ui.gua

sealed interface GuaExplainUiState {
    data object Loading: GuaExplainUiState
    data object LoadFailed: GuaExplainUiState

    data class Success(
        val base: String = "",
        val explain: String = ""
    ): GuaExplainUiState{
        fun isEmpty() = base.isEmpty() && explain.isEmpty()
    }
}