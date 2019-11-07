package ru.sample.presentation.internal.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.sample.data.net.SoftPosApi

@Module(includes = arrayOf(NetModule::class))
class RestModule {

    @Provides
    fun provideSoftPosApi(retrofit: Retrofit) = retrofit.create(SoftPosApi::class.java)
}