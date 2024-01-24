package com.clwater.androidudf.data.repository.di

import com.clwater.androidudf.data.repository.DatabaseRepository
import com.clwater.androidudf.data.repository.DefaultDatabaseRepository
import com.clwater.androidudf.data.repository.DefaultGuaRepository
import com.clwater.androidudf.data.repository.GuaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule
{
    @Binds
    internal abstract fun bindYaoRepository(
        yaoRepository: DefaultGuaRepository,
    ): GuaRepository

    @Binds
    internal abstract fun bindDatabaseRepository(
        databaseRepository: DefaultDatabaseRepository,
    ): DatabaseRepository
}