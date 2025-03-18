package com.nicole_u_latina_araya_solano.di

import android.content.Context
import com.nicole_u_latina_araya_solano.data.HouseNicoleRepository
import com.nicole_u_latina_araya_solano.data.database.AppDatabase
import com.nicole_u_latina_araya_solano.data.database.interfaces.HouseNicoleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideItemDao(appDatabase: AppDatabase): HouseNicoleDao {
        return appDatabase.HouseNicoleDao()
    }

    @Provides
    @Singleton
    fun provideItemRepository(houseNicoleDao: HouseNicoleDao): HouseNicoleRepository {
        return HouseNicoleRepository(houseNicoleDao)
    }

}