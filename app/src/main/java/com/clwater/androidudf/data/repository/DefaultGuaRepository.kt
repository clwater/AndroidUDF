package com.clwater.androidudf.data.repository

import com.clwater.androidudf.core.model.data.GuaExplainResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class DefaultGuaRepository @Inject constructor() :
    GuaRepository {
    override  fun getExplainInfo(index: Int): Flow<GuaExplainResult> {
        return if (index > 0){
            Thread.sleep(3000)
            flowOf(GuaExplainResult("base $index", "explain $index"))
        }else{
            flowOf(GuaExplainResult())
        }
    }
}