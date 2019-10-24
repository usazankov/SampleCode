package ru.sample.presentation.internal.di.modules

import dagger.Module

@Module(includes = arrayOf(DataModule::class))
class SoftPosModule {
}