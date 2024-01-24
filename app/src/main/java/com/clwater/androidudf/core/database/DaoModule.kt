package com.clwater.androidudf.core.database

import com.clwater.androidudf.core.database.GuaDatabase
import com.clwater.androidudf.core.database.dao.GuaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun provideGuaDao(
        database: GuaDatabase,
    ): GuaDao = database.guaDao()
}