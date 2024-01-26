package com.clwater.androidudf.data.repository

import com.clwater.androidudf.core.model.data.GuaExplainResult
import kotlinx.coroutines.flow.Flow

interface GuaRepository {
    suspend fun getExplainInfo(index: Int): Flow<GuaExplainResult>

}