package ru.sample.presentation.internal.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.sample.data.executor.JobExecutor
import ru.sample.domain.executor.PostExecutionThread
import ru.sample.domain.executor.ThreadExecutor
import ru.sample.presentation.AndroidApplication
import ru.sample.presentation.UIThread
import javax.inject.Singleton

@Module
class ApplicationModule(val androidApplication: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = androidApplication.applicationContext

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

}