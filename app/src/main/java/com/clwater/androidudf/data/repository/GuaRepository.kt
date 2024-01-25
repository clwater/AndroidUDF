package com.clwater.androidudf.data.repository

import com.clwater.androidudf.core.model.data.GuaExplainResult
import kotlinx.coroutines.flow.Flow

interface GuaRepository {
    fun getExplainInfo(index: Int): Flow<GuaExplainResult>

}