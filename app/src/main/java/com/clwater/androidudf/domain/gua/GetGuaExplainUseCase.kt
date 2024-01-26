package com.clwater.androidudf.domain.gua

import com.clwater.androidudf.data.repository.GuaRepository
import com.clwater.androidudf.core.model.data.GuaExplainResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGuaExplainUseCase @Inject constructor(
    private val guaRepository: GuaRepository
) {
    suspend operator fun invoke(index: Int): Flow<GuaExplainResult> =
        guaRepository.getExplainInfo(index)
}