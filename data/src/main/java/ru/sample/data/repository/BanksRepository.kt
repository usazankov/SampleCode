package ru.sample.data.repository

import io.reactivex.Observable
import ru.sample.data.net.SoftPosApi
import ru.sample.data.repository.datasource.BankDataStoreFactory
import ru.sample.data.repository.datasource.cache.IBanksCache
import ru.sample.domain.entity.BankManifestEntity
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ShortBankEntity
import ru.sample.domain.repository.IBanksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BanksRepository @Inject constructor(val bankDataStoreFactory: BankDataStoreFactory) : IBanksRepository {

    override fun listBanks(): Observable<List<ShortBankEntity>> =
        bankDataStoreFactory.createWithCacheListBanks().listBanks()

    override fun bankDescription(bankId: Int): Observable<FullBankEntity> =
        bankDataStoreFactory.createWithCacheDetailsBank(bankId).bankDescription(bankId)

    override fun bankManifest(bankId: Int): Observable<BankManifestEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}