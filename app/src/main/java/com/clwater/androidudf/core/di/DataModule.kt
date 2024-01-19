package com.clwater.androidudf.core.di

import com.clwater.androidudf.data.DefaultYaoRepository
import com.clwater.androidudf.data.YaoRepository
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
        yaoRepository: DefaultYaoRepository,
    ): YaoRepository
}