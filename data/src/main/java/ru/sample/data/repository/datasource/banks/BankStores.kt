package ru.sample.data.repository.datasource.banks

import io.reactivex.Observable
import ru.sample.data.entity.BankManifestEntity
import ru.sample.data.entity.FullBankEntity
import ru.sample.data.entity.ShortBankEntity

interface IBankDataStore{
    fun listBanks(): Observable<List<ShortBankEntity>>
    fun bankDescription(bankId: Int): Observable<FullBankEntity>
    fun bankManifest(bankId: Int): Observable<BankManifestEntity>
}

class CloudBankDataStore : IBankDataStore {
    override fun listBanks(): Observable<List<ShortBankEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bankDescription(bankId: Int): Observable<FullBankEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bankManifest(bankId: Int): Observable<BankManifestEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}