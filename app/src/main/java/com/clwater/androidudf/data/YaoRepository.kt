package com.clwater.androidudf.data

import com.clwater.androidudf.core.model.data.YaoExplainResult
import kotlinx.coroutines.flow.Flow

interface YaoRepository {
    fun getExplainInfo(index: Int): Flow<YaoExplainResult>
}