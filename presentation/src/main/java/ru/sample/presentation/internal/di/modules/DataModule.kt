package ru.sample.presentation.internal.di.modules

import dagger.Module
import dagger.Provides
import ru.sample.data.repository.BanksRepository
import ru.sample.data.repository.datasource.cache.BanksCacheImpl
import ru.sample.data.repository.datasource.cache.IBanksCache
import ru.sample.domain.repository.IBanksRepository
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideBanksCache(banksCache: BanksCacheImpl): IBanksCache {
        return banksCache
    }

    @Provides
    @Singleton
    fun provideBanksRepository(banksRepository: BanksRepository): IBanksRepository {
        return banksRepository
    }
}