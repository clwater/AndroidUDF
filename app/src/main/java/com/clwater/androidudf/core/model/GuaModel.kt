package com.clwater.androidudf.core.model

import com.clwater.androidudf.core.database.model.GuaEntity

data class GuaModel(
    val id: Int,
    val name: String,
    val image: String,
    val desc: String,
    val desc_group: String,
    val detail: String
) {
    fun toEntity(): GuaEntity {
       return GuaEntity(
           id, name, desc, desc_group, image, detail
       )
    }
}
