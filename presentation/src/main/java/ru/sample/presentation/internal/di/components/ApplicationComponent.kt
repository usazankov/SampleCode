package ru.sample.presentation.internal.di.components

import dagger.Component
import ru.sample.presentation.internal.di.modules.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
}