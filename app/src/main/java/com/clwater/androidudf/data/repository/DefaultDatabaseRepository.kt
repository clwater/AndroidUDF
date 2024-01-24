package com.clwater.androidudf.data.repository

import com.clwater.androidudf.core.database.dao.GuaDao
import com.clwater.androidudf.core.utils.ConfigUtils
import javax.inject.Inject

class DefaultDatabaseRepository @Inject constructor(
    private val configUtils: ConfigUtils,
    private val guaDao: GuaDao
) :
    DatabaseRepository {
    override fun guaTableInit() {
        if (guaDao.count() == 0){
            val guaModels = configUtils.getGuaBaseInfo()
            guaModels.map {
                guaDao.insertAll(it.toEntity())
            }
        }
    }

}