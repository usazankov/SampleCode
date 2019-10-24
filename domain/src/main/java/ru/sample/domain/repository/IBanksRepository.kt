package ru.sample.domain.repository

import io.reactivex.Observable
import ru.sample.domain.entity.BankManifestEntity
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ShortBankEntity

interface IBanksRepository {

    /**
     *
     * @return list of supported banks
     */
    fun listBanks(): Observable<List<ShortBankEntity>>

    /**
     *
     * @return description of bank
     */
    fun bankDescription(bankId: Int): Observable<FullBankEntity>

    /**
     *
     * @return manifest of bank
     */
    fun bankManifest(bankId: Int): Observable<BankManifestEntity>
}