package com.clwater.androidudf.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GuaEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "desc")
    val desc: String,
    @ColumnInfo(name = "desc_group")
    val descGroup: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "detail")
    val detail: String,
)
