package com.clwater.androidudf.data.repository

import com.clwater.androidudf.core.database.dao.GuaDao
import com.clwater.androidudf.core.database.model.GuaEntity
import com.clwater.androidudf.core.model.data.GuaBaseResult
import com.clwater.androidudf.core.utils.ConfigUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
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
                val _guaEntity = it.toEntity()
                val guaEntity = GuaEntity(
                    id = _guaEntity.id,
                    name = _guaEntity.name,
                    image = _guaEntity.image.replace(",", ""),
                    desc = _guaEntity.desc,
                    descGroup = _guaEntity.descGroup,
                    detail = _guaEntity.detail
                )
                guaDao.insertAll(guaEntity)
            }
        }
    }

    override fun getGuaBaseInfo(imageList: List<Boolean>): Flow<GuaBaseResult> {
        var images = ""
        imageList.map {
            images += if (it) "1" else "0"
        }
        val guaEntity = guaDao.getGuaByImage(images)
        return flowOf(GuaBaseResult(guaEntity.id, guaEntity.name, guaEntity.detail, guaEntity.descGroup))
    }

}