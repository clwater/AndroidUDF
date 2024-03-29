package com.clwater.androidudf.ui.gua

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
internal fun GuaScreenRoute(
    guaViewModel: GuaViewModel = hiltViewModel()
) {
    guaViewModel.initDatabase()
    val yaoExplainUiState  by guaViewModel.getGuaExplainUiState.collectAsStateWithLifecycle()
    val yaoUiState by guaViewModel.getYaoUIState.collectAsStateWithLifecycle()
    GuaScreen(
        testClick = guaViewModel::onSearchQueryChanged,
        yaoChange = guaViewModel::onYaoChanged,
        guaExplainUiState = yaoExplainUiState,
        yaoUiState = yaoUiState
    )
}

@Composable
internal fun GuaScreen(
    testClick: (Int) -> Unit = {},
    yaoChange: (Int) -> Unit = {},
    guaExplainUiState: GuaExplainUiState = GuaExplainUiState.LoadFailed,
    yaoUiState: GuaBaseUiState = GuaBaseUiState()
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF468E8D))) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = yaoUiState.name,
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                fontSize = if (yaoUiState.name.length > 1) 200.sp else 300.sp,
                color = Color(0xFF66a09f),
                maxLines = 1,
            )
        }

        Column(
            modifier = Modifier
        ) {

            LiuYao(30.dp, yaoChange, yaoUiState.yaos)

            Column(modifier = Modifier.weight(1f).padding(16.dp),
                verticalArrangement = Arrangement.Bottom) {
                when (guaExplainUiState) {
                    GuaExplainUiState.Loading -> Text(color = Color(0xFF105150),
                        text = "Loading...")
                    GuaExplainUiState.LoadFailed -> Text(color = Color(0xFF105150),text = "Fail")
                    is GuaExplainUiState.Success -> {
//                        Text(text = "Success")
                        Text(color = Color(0xFF105150),text = guaExplainUiState.base)
                        Text(color = Color(0xFF105150),text = guaExplainUiState.explain)
                    }

                    else -> {}
                }

            }


            Column(
                modifier = Modifier, verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                    color = Color(0xFF105150),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    text = yaoUiState.title
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    color = Color(0xFF105150),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    text = yaoUiState.detail
                )
            }
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