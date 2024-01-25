package com.clwater.androidudf.domain.gua

import com.clwater.androidudf.core.model.data.GuaBaseResult
import com.clwater.androidudf.data.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGuaBaseUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    operator fun invoke(imageList: List<Boolean>): Flow<GuaBaseResult> =
        databaseRepository.getGuaBaseInfo(imageList)
}