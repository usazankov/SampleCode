package ru.sample.presentation.internal.di.components

import android.content.Context
import dagger.Component
import ru.sample.presentation.internal.di.modules.ApplicationModule
import ru.sample.presentation.view.activity.BaseActivity
import ru.sample.presentation.view.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(mainActivity: MainActivity)
    fun context(): Context
}