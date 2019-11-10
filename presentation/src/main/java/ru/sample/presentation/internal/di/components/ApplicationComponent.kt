package ru.sample.presentation.internal.di.components

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Component
import ru.sample.presentation.internal.di.modules.ApplicationModule
import ru.sample.presentation.internal.di.modules.SoftPosModule
import ru.sample.presentation.view.activity.BaseActivity
import ru.sample.presentation.view.activity.MainActivity
import ru.sample.presentation.view.presenter.BankDetailsPresenter
import ru.sample.presentation.view.presenter.SelectBankPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, SoftPosModule::class))
interface ApplicationComponent {

    //Activity
    fun inject(baseActivity: BaseActivity)
    fun inject(mainActivity: MainActivity)

    //Picasso
    fun picasso(): Picasso

    //Context
    fun context(): Context

    //Presenters
    fun inject(selectBankPresenter: SelectBankPresenter)
    fun inject(bankDetailsPresenter: BankDetailsPresenter)

}