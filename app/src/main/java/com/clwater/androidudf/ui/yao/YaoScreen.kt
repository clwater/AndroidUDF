package com.clwater.androidudf.ui.yao

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
internal fun YaoScreenRoute(
    yaoViewModel: YaoViewModel = hiltViewModel()
) {
    val yaoExplainUiState  by yaoViewModel.getYaoExplainUiState.collectAsStateWithLifecycle()
    val yaoUiState by yaoViewModel.getYaoUIState.collectAsStateWithLifecycle()
    YaoScreen(
        testClick = yaoViewModel::onSearchQueryChanged,
        yaoChange = yaoViewModel::onYaoChanged,
        yaoExplainUiState = yaoExplainUiState,
        yaoUiState = yaoUiState
    )
}

@Composable
internal fun YaoScreen(
    testClick: (Int) -> Unit = {},
    yaoChange: (Int) -> Unit = {},
    yaoExplainUiState: YaoExplainUiState = YaoExplainUiState.LoadFailed,
    yaoUiState: YaoBaseUiState = YaoBaseUiState()
) {
    Column {
        for (i in  yaoUiState.yaos.size downTo  1   ){

            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    yaoChange(i - 1)
                }) {
                Text(text = "${yaoUiState.yaos[i - 1]}")
            }
        }

        when(yaoExplainUiState){
            YaoExplainUiState.Loading -> Text(text = "Loading")
            YaoExplainUiState.LoadFailed  -> Text(text = "Fail")
            is YaoExplainUiState.Success -> {
                Text(text = "Success")
                Text(text = yaoExplainUiState.base)
                Text(text = yaoExplainUiState.explain)
            }

            else -> {}
        }

        Button(onClick = { testClick(10) }) {
            Text(text = "test")
        }
    }
}