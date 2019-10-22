package ru.sample.data.net

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ru.sample.data.entity.BankManifestEntity
import ru.sample.data.entity.FullBankEntity
import ru.sample.data.entity.ServiceDescriptionEntity
import ru.sample.data.entity.ShortBankEntity

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