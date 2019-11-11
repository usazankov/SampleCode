package ru.sample.data.net

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ItemTariff
import ru.sample.domain.entity.ServiceDescriptionEntity
import ru.sample.domain.entity.ShortBankEntity
import java.util.concurrent.TimeUnit


@RunWith(MockitoJUnitRunner::class)
class SoftPosApiTest {

    lateinit var retrofit: Retrofit;
    lateinit var mockWebServer: MockWebServer;

    val get = "GET"
    val post = "POST"

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @After
    fun close(){
        mockWebServer.shutdown()
    }

    @Test
    fun setviceDescrTest(){
        mockWebServer.enqueue(MockResponse().setBody(ServiceDescrRespnonse.JSON_SERVICE_DESCR))
        val service = retrofit.create(SoftPosApi::class.java)
        val call = service.serviceDescription()
        var serviceDescr: ServiceDescriptionEntity? = null
        call.subscribe{
            serviceDescr = it
        }
        val req = mockWebServer.takeRequest(1, TimeUnit.SECONDS)
        assertEquals(req.method, get)
        assertEquals(req.path, "/softpos/info")
        assertNotNull(serviceDescr)
        assertEquals(serviceDescr?.coverUrl,
            ServiceDescrRespnonse.coverUrl
        )
        assertEquals(serviceDescr?.description,
            ServiceDescrRespnonse.descr
        )
        assertEquals(serviceDescr?.logoUrl,
            ServiceDescrRespnonse.logoUrl
        )
        assertEquals(serviceDescr?.name, ServiceDescrRespnonse.name)
        assertEquals(serviceDescr?.phone, ServiceDescrRespnonse.phone)
    }

    @Test
    fun bankListTest(){
        mockWebServer.enqueue(MockResponse().setBody(BankListResponse.JSON_BANK_LIST))
        val service = retrofit.create(SoftPosApi::class.java)
        val call = service.listBanks()
        var bankList: List<ShortBankEntity>? = null
        call.subscribe{
            bankList = it
        }
        val req = mockWebServer.takeRequest(1, TimeUnit.SECONDS)
        assertEquals(req.method, get)
        assertEquals(req.path, "/softpos/banks")
        assertNotNull(bankList)
        assertEquals(bankList?.size, 2)
        val bank1: ShortBankEntity? = bankList?.get(0)
        val bank2: ShortBankEntity? = bankList?.get(1)

        //Bank1
        assertEquals(bank1?.fullName, BankListResponse.fullName_1)
        assertEquals(bank1?.shortName, BankListResponse.shortName_1)
        assertEquals(bank1?.color, BankListResponse.color_1)
        assertEquals(bank1?.id, BankListResponse.id_1)
        assertEquals(bank1?.coverUrl, BankListResponse.coverUrl_1)
        assertEquals(bank1?.smallIconUrl, BankListResponse.smallIcon_1)

        //Bank2
        assertEquals(bank2?.fullName, BankListResponse.fullName_2)
        assertEquals(bank2?.shortName, BankListResponse.shortName_2)
        assertEquals(bank2?.color, BankListResponse.color_2)
        assertEquals(bank2?.id, BankListResponse.id_2)
        assertEquals(bank2?.coverUrl, BankListResponse.coverUrl_2)
        assertEquals(bank2?.smallIconUrl, BankListResponse.smallIcon_2)
    }

    @Test
    fun bankDescrTest(){
        mockWebServer.enqueue(MockResponse().setBody(BankDescrRespnonse.JSON_BANK_DESCR))
        val service = retrofit.create(SoftPosApi::class.java)
        val id = 0
        val call = service.bankDescription(id)
        var bankDescr: FullBankEntity? = null
        call.subscribe{
            bankDescr = it
        }
        val req = mockWebServer.takeRequest(1, TimeUnit.SECONDS)
        assertEquals(req.method, get)
        assertEquals(req.path, "/softpos/banks/${id}")
        assertNotNull(bankDescr)
        assertEquals(bankDescr?.coverUrl, BankDescrRespnonse.coverUrl)
        assertEquals(bankDescr?.fullName, BankDescrRespnonse.fullName)
        assertEquals(bankDescr?.shortName,
            BankDescrRespnonse.shortName
        )
        assertEquals(bankDescr?.color, BankDescrRespnonse.color)
        assertEquals(bankDescr?.id, BankDescrRespnonse.id)
        assertEquals(bankDescr?.offer, BankDescrRespnonse.offer)
        assertEquals(bankDescr?.smallIconUrl,
            BankDescrRespnonse.smallIconUrl
        )
        assertEquals(bankDescr?.textFullTariffs,
            BankDescrRespnonse.textUrl
        )
        assertEquals(bankDescr?.urlFullTariffs, BankDescrRespnonse.url)
        val tariffs: List<ItemTariff>? = bankDescr?.tariffs
        assertEquals(tariffs?.size, BankDescrRespnonse.countTariff)
        assertEquals(tariffs?.get(0)?.name, BankDescrRespnonse.name1)
        assertEquals(tariffs?.get(0)?.value, BankDescrRespnonse.val1)
        assertEquals(tariffs?.get(1)?.name, BankDescrRespnonse.name2)
        assertEquals(tariffs?.get(1)?.value, BankDescrRespnonse.val2)
        assertEquals(tariffs?.get(2)?.name, BankDescrRespnonse.name3)
        assertEquals(tariffs?.get(2)?.value, BankDescrRespnonse.val3)
        assertEquals(tariffs?.get(3)?.name, BankDescrRespnonse.name4)
        assertEquals(tariffs?.get(3)?.value, BankDescrRespnonse.val4)
        assertEquals(tariffs?.get(4)?.name, BankDescrRespnonse.name5)
        assertEquals(tariffs?.get(4)?.value, BankDescrRespnonse.val5)
    }
}