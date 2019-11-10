package ru.sample.presentation.view.interfaces

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ShortBankEntity


interface BankDetailsView : LoadDataView, MvpView{

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderBankDetails(bankEntity: FullBankEntity)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderBankPager(bankEntityList: List<ShortBankEntity>)

    @StateStrategyType(SkipStrategy::class)
    fun viewLoadBankDetails()

    @StateStrategyType(SkipStrategy::class)
    fun hideLoadBankDetails()
}