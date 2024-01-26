package com.clwater.androidudf.core

import com.clwater.androidudf.core.model.data.GuaExplainResult
import kotlinx.coroutines.delay
import kotlin.random.Random

object  FakeNetwork {
    suspend fun getGuaFakeExplain(index: Int): GuaExplainResult {
        delay(3000)
        return GuaExplainResult("FakeBase", "Fake Explain")
    }

}