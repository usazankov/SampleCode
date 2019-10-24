package ru.sample.data.net

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ru.sample.domain.entity.BankManifestEntity
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ServiceDescriptionEntity
import ru.sample.domain.entity.ShortBankEntity

interface SoftPosApi {

    @GET("/softpos/info")
    fun serviceDescription(): Observable<ServiceDescriptionEntity>

    @GET("/softpos/banks")
    fun listBanks(): Observable<List<ShortBankEntity>>

    @GET("/softpos/banks/{id}")
    fun bankDescription(@Path("id")bankId: Int): Observable<FullBankEntity>

    @GET("/softpos/banks/manifest/{id}")
    fun bankManifest(@Path("id")bankId: Int): Observable<BankManifestEntity>

}