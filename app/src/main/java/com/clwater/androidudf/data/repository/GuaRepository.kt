package com.clwater.androidudf.data.repository

import com.clwater.androidudf.core.model.data.YaoExplainResult
import kotlinx.coroutines.flow.Flow

interface GuaRepository {
    fun getExplainInfo(index: Int): Flow<YaoExplainResult>

}