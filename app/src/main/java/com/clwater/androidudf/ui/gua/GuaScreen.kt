package com.clwater.androidudf.ui.gua

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
internal fun YaoScreenRoute(
    guaViewModel: GuaViewModel = hiltViewModel()
) {
    guaViewModel.initDatabase()
    val yaoExplainUiState  by guaViewModel.getGuaExplainUiState.collectAsStateWithLifecycle()
    val yaoUiState by guaViewModel.getYaoUIState.collectAsStateWithLifecycle()
    YaoScreen(
        testClick = guaViewModel::onSearchQueryChanged,
        yaoChange = guaViewModel::onYaoChanged,
        guaExplainUiState = yaoExplainUiState,
        yaoUiState = yaoUiState
    )
}

@Composable
internal fun YaoScreen(
    testClick: (Int) -> Unit = {},
    yaoChange: (Int) -> Unit = {},
    guaExplainUiState: GuaExplainUiState = GuaExplainUiState.LoadFailed,
    yaoUiState: GuaBaseUiState = GuaBaseUiState()
) {
    Column(
        modifier = Modifier.background(Color(0xFF468E8D))
    ) {

        LiuYao(30.dp, yaoChange, yaoUiState.yaos)
        Text(text = yaoUiState.name)

        when(guaExplainUiState){
            GuaExplainUiState.Loading -> Text(text = "Loading")
            GuaExplainUiState.LoadFailed  -> Text(text = "Fail")
            is GuaExplainUiState.Success -> {
                Text(text = "Success")
                Text(text = guaExplainUiState.base)
                Text(text = guaExplainUiState.explain)
            }

            else -> {}
        }

        Button(onClick = { testClick(10) }) {
            Text(text = "test")
        }
    }
}

@Composable
internal fun LiuYao(
    itemHeight: Dp = 0.dp,
    yaoChange: (Int) -> Unit = {},
    yaoList: List<Boolean> = listOf(true, true, true, true, true, true)
){
    Column(
        modifier = Modifier
    ) {
        for (i in  yaoList.size downTo  1   ){

            Row(modifier = Modifier
                .padding(itemHeight / 10f * 5)
                .height(itemHeight)
                .fillMaxWidth()
                .clickable {
                    yaoChange(i - 1)
                }) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = Color.White)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.1f)
                        .fillMaxHeight()
                        .background(color = if (yaoList[i - 1]) Color.White else Color.Transparent)
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = Color.White)
                )
            }
            if (i == yaoList.size / 2 + 1){
                Box(modifier = Modifier
                    .height(itemHeight / 2f)
                    .fillMaxWidth()){
                }
            }
        }
    }
}