package ru.sample.presentation.internal.di.modules

import dagger.Module
import dagger.Provides
import ru.sample.data.repository.datasource.cache.BanksCacheImpl
import ru.sample.data.repository.datasource.cache.IBanksCache
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideBanksCache(banksCache: BanksCacheImpl): IBanksCache {
        return banksCache
    }
}