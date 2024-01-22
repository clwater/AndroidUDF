package com.clwater.androidudf.ui.yao

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
internal fun YaoScreenRoute(
    yaoViewModel: YaoViewModel = hiltViewModel()
) {
    val yaoUiState  by yaoViewModel.getYaoUiState.collectAsStateWithLifecycle()
    YaoScreen(
        testClick = yaoViewModel::onSearchQueryChanged,
        yaoUiState = yaoUiState
    )
}

@Composable
internal fun YaoScreen(
    testClick: (Int) -> Unit = {},
    yaoUiState: YaoUiState = YaoUiState.LoadFailed
) {
    Column {
        when(yaoUiState){
            YaoUiState.Loading -> Text(text = "Loading")
            YaoUiState.LoadFailed  -> Text(text = "Fail")
            is YaoUiState.Success -> {
                Text(text = "Success")
                Text(text = yaoUiState.base)
                Text(text = yaoUiState.explain)
            }
        }

        Button(onClick = { testClick(10) }) {
            Text(text = "test")
        }
    }
}