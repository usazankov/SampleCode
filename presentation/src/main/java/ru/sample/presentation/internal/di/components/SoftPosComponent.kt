package ru.sample.presentation.internal.di.components

import dagger.Component
import ru.sample.presentation.internal.di.modules.SoftPosModule
import javax.inject.Singleton

@Singleton
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(SoftPosModule::class))
interface SoftPosComponent : ApplicationComponent {
}