package ru.sample.presentation.internal.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import java.security.KeyStore
import java.security.cert.CertificateFactory

@Module
class KeyStoreModule {

    @Provides
    fun keyStore(context: Context): KeyStore{
        val certificateFactory = CertificateFactory.getInstance("X.509")
        val keyStore = KeyStore.getInstance("PKCS12")
        val inputCa = context.assets.open("default-ca.pem")
        keyStore.load(null, null)
        keyStore.setCertificateEntry("inpas.ru", certificateFactory.generateCertificate(inputCa))
        return keyStore;
    }
}