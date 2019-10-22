package ru.sample.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sample.data.net.SoftPosApi


@RunWith(MockitoJUnitRunner::class)
class SoftPosApiTest {
    val coverUrl: String = "main_image.png"
    val JSON_SERVICE_DESCR: String = "{\"coverUrl\":\"${coverUrl}\",\"description\":\"Описание сервиса в двух-трех предложениях. Цель этого описания быстро объяснить как здорово пользоваться этим сервисом. Какие-то слова для утверждения, что сервис лучше кардридеров от банков.\",\"logoUrl\":\"logo.png\",\"name\":\"SoftPos\",\"technicalSupportPhone\":\"+7 (111) 222 3333\"}"

    @Test
    fun setviceDescrTest(){
        val mockWebServer = MockWebServer()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        //Set a response for retrofit to handle. You can copy a sample
        //response from your server to simulate a correct result or an error.
        //MockResponse can also be customized with different parameters
        //to match your test needs
        mockWebServer.enqueue(MockResponse().setBody(JSON_SERVICE_DESCR))

        val service = retrofit.create(SoftPosApi::class.java)

        //With your service created you can now call its method that should
        //consume the MockResponse above. You can then use the desired
        //assertion to check if the result is as expected. For example:
        val call = service.serviceDescription()
        call.subscribe{
            it.coverUrl
        }

        //Finish web server
        mockWebServer.shutdown()
    }
}