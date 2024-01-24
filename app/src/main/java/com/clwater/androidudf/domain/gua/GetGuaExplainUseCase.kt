package com.clwater.androidudf.domain.gua

import com.clwater.androidudf.data.repository.GuaRepository
import com.clwater.androidudf.core.model.data.YaoExplainResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGuaExplainUseCase @Inject constructor(
    private val guaRepository: GuaRepository
) {
    operator fun invoke(index: Int): Flow<YaoExplainResult> =
        guaRepository.getExplainInfo(index)
}