package com.clwater.androidudf.ui.yao

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
internal fun YaoScreenRoute(
    yaoViewModel: YaoViewModel = hiltViewModel()
) {
    yaoViewModel.initDatabase()
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
    Column(
        modifier = Modifier.background(Color(0xFF468E8D))
    ) {

        LiuYao(30.dp, yaoChange, yaoUiState.yaos)

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