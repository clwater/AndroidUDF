package com.clwater.androidudf.core.database

import android.content.Context
import androidx.room.Room
import com.clwater.androidudf.core.database.GuaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideGuaDatabase(
        @ApplicationContext context: Context,
    ): GuaDatabase = Room.databaseBuilder(
        context,
        GuaDatabase::class.java,
        "gua-database",
    ).allowMainThreadQueries()
        .build()
}