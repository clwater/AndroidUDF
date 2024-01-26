package com.clwater.androidudf.ui.gua

data class  GuaBaseUiState (
    val yaos: List<Boolean> = listOf(true, true, true, true, true, true),
    val id: Int = 0,
    val name: String = "",
    val title: String = "",
    val detail: String = "",
)