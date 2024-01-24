package com.clwater.androidudf.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.clwater.androidudf.core.database.model.GuaEntity


@Dao
interface GuaDao {
    @Query("SELECT COUNT(id) From GuaEntity")
    fun count(): Int

    @Query("SELECT * FROM GuaEntity WHERE id = :id")
    fun getGuaById(id: Int): GuaEntity

    @Query("Select * FROM GuaEntity WHERE image = :image")
    fun getGuaByImage(image: String): GuaEntity

    @Insert
    fun insertAll(vararg gua: GuaEntity)

    @Query("Delete FROM GuaEntity")
    fun deleteAll()

}