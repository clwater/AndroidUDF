package com.clwater.androidudf.data.repository

import com.clwater.androidudf.core.model.data.GuaBaseResult
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    fun guaTableInit()
    fun getGuaBaseInfo(imageList: List<Boolean>): Flow<GuaBaseResult>
}