package ru.sample.presentation.internal.di.components

import dagger.Component
import ru.sample.presentation.internal.di.PerActivity
import ru.sample.presentation.internal.di.modules.ActivityModule
import ru.sample.presentation.internal.di.modules.SoftPosModule
import javax.inject.Singleton

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class, SoftPosModule::class))
interface SoftPosComponent : ActivityComponent {
}