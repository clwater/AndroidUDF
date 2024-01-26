package com.clwater.androidudf.data.repository

import com.clwater.androidudf.core.FakeNetwork
import com.clwater.androidudf.core.model.data.GuaExplainResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class DefaultGuaRepository @Inject constructor() :
    GuaRepository {
    override suspend fun getExplainInfo(index: Int): Flow<GuaExplainResult> {
        return flowOf(FakeNetwork.getGuaFakeExplain(index))
    }
}