package com.clwater.androidudf.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clwater.androidudf.core.database.dao.GuaDao
import com.clwater.androidudf.core.database.model.GuaEntity

@Database(
    entities = [
        GuaEntity::class
    ],
    version = 1,
    exportSchema = true
)
internal abstract class GuaDatabase : RoomDatabase(){
    abstract fun guaDao(): GuaDao
}