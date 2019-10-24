package ru.sample.data.repository.datasource

import io.reactivex.Observable
import ru.sample.data.net.SoftPosApi
import ru.sample.data.repository.datasource.cache.IBanksCache
import ru.sample.domain.entity.BankManifestEntity
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ShortBankEntity
import javax.inject.Inject
import javax.inject.Singleton

interface IBankDataStore{
    fun listBanks(): Observable<List<ShortBankEntity>>
    fun bankDescription(bankId: Int): Observable<FullBankEntity>
    fun bankManifest(bankId: Int): Observable<BankManifestEntity>
}

@Singleton
class BankDataStoreFactory @Inject constructor(
    private val api: SoftPosApi,
    private val banksCache: IBanksCache
) {

    /**
     * Create [IBankDataStore] from a bank id.
     */
    fun createWithCacheDetailsBank(bankId: Int): IBankDataStore {
        val bankDataStore: IBankDataStore

        if (!this.banksCache.isExpired() && this.banksCache.isCached(bankId)) {
            bankDataStore = DiskBankDataStore(this.banksCache)
        } else {
            bankDataStore = CloudBankDataStore(api, banksCache)
        }
        return bankDataStore
    }

    /**
     * Create [IBankDataStore] from a bank id.
     */
    fun createWithCacheListBanks(): IBankDataStore {
        val bankDataStore: IBankDataStore

        if (!this.banksCache.isExpired() && this.banksCache.isCachedBankList()) {
            bankDataStore = DiskBankDataStore(this.banksCache)
        } else {
            bankDataStore = CloudBankDataStore(api, banksCache)
        }
        return bankDataStore
    }

    /**
     * Create [IBankDataStore] from a bank id.
     */
//    fun createWithCacheManifest(bankId: Int): IBankDataStore {
//        val bankDataStore: IBankDataStore
//        if (!this.banksManifestCache.isExpired() && this.banksManifestCache.isCached(bankId)) {
//            bankDataStore = DiskBankDataStore(this.banksCache, this.banksManifestCache)
//        } else {
//            bankDataStore = createDataStore()
//        }
//
//        return bankDataStore
//    }

}

class CloudBankDataStore @Inject constructor(private val api: SoftPosApi, private val banksCache: IBanksCache) :
    IBankDataStore {

    override fun listBanks(): Observable<List<ShortBankEntity>> {
        return api.listBanks()
            .doOnNext { banksCache.putBankList(it) }
    }

    override fun bankDescription(bankId: Int): Observable<FullBankEntity> {
        return api.bankDescription(bankId)
            .doOnNext { banksCache.putBank(it) }
    }

    override fun bankManifest(bankId: Int): Observable<BankManifestEntity> {
        return api.bankManifest(bankId)
    }

}

class DiskBankDataStore(
    private val banksCache: IBanksCache
) : IBankDataStore {

    override fun listBanks(): Observable<List<ShortBankEntity>> {
        return banksCache.getBankList()
    }

    override fun bankDescription(bankId: Int): Observable<FullBankEntity> {
        return banksCache.getBank(bankId)
    }

    override fun bankManifest(bankId: Int): Observable<BankManifestEntity> {
        return Observable.create { it.onComplete() }
    }
}