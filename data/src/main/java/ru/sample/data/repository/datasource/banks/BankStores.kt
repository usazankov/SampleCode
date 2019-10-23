package ru.sample.data.repository.datasource.banks

import io.reactivex.Observable
import ru.sample.data.entity.BankManifestEntity
import ru.sample.data.entity.FullBankEntity
import ru.sample.data.entity.ShortBankEntity
import ru.sample.data.net.SoftPosApi
import javax.inject.Inject

interface IBankDataStore{
    fun listBanks(): Observable<List<ShortBankEntity>>
    fun bankDescription(bankId: Int): Observable<FullBankEntity>
    fun bankManifest(bankId: Int): Observable<BankManifestEntity>
}

class CloudBankDataStore @Inject constructor(val api: SoftPosApi) : IBankDataStore {

    override fun listBanks(): Observable<List<ShortBankEntity>> {
        return api.listBanks()
    }

    override fun bankDescription(bankId: Int): Observable<FullBankEntity> {
        return api.bankDescription(bankId)
    }

    override fun bankManifest(bankId: Int): Observable<BankManifestEntity> {
        return api.bankManifest(bankId)
    }

}