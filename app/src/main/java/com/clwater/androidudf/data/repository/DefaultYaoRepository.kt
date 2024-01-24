package com.clwater.androidudf.data.repository

import com.clwater.androidudf.core.model.data.YaoExplainResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class DefaultYaoRepository @Inject constructor() :
    YaoRepository {
    override  fun getExplainInfo(index: Int): Flow<YaoExplainResult> {
        return if (index > 0){
            Thread.sleep(3000)
            flowOf(YaoExplainResult("base $index", "explain $index"))
        }else{
            flowOf(YaoExplainResult())
        }
    }
}