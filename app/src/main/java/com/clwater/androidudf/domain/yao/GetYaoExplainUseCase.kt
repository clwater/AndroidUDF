package com.clwater.androidudf.domain.yao

import com.clwater.androidudf.data.repository.YaoRepository
import com.clwater.androidudf.core.model.data.YaoExplainResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetYaoExplainUseCase @Inject constructor(
    private val yaoRepository: YaoRepository
) {
    operator fun invoke(index: Int): Flow<YaoExplainResult> =
        yaoRepository.getExplainInfo(index)
}