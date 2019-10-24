package ru.sample.data.repository.datasource.cache

import io.reactivex.Observable
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ShortBankEntity

interface IBanksCache : ICache{

    /**
     * Gets an [Observable] which will emit a [FullBankEntity].
     *
     * @param bankId The bank id to retrieve data.
     */
    fun getBank(bankId: Int): Observable<FullBankEntity>

    /**
     * Gets an [Observable] which will emit a [FullBankEntity].
     *
     */
    fun getBankList(): Observable<List<ShortBankEntity>>


    /**
     * Puts and element into the cache.
     *
     * @param bankEntity Element to insert in the cache.
     */
    fun putBankList(bankEntity: List<ShortBankEntity>)

    /**
     * Puts and element into the cache.
     *
     * @param bankEntity Element to insert in the cache.
     */
    fun putBank(bankEntity: FullBankEntity)

    /**
     * Checks if an element (bank) exists in the cache.
     *
     * @param bankId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(bankId: Int): Boolean

    /**
     * Checks if an element (bank) exists in the cache.
     *
     * @return true if the element is cached, otherwise false.
     */
    fun isCachedBankList(): Boolean

}