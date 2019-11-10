package ru.sample.presentation.internal.di.components

import dagger.Component
import ru.sample.presentation.internal.di.PerActivity
import ru.sample.presentation.internal.di.modules.ActivityModule
import ru.sample.presentation.view.fragment.BankDetailsFragment
import ru.sample.presentation.view.fragment.SelectBankFragment

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface SoftPosComponent : ActivityComponent {
    fun inject(selectBankFragment: SelectBankFragment)
    fun inject(bankDetailsFragment: BankDetailsFragment)
}