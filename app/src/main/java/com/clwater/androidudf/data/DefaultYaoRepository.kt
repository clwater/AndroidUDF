package com.clwater.androidudf.data

import com.clwater.androidudf.core.model.data.YaoExplainResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class DefaultYaoRepository @Inject constructor() :
    YaoRepository{
    override fun getExplainInfo(index: Int): Flow<YaoExplainResult> {
        return flowOf(YaoExplainResult("base $index", "explain $index"))
    }
}