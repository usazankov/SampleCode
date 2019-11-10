package ru.sample.presentation.view.interfaces

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.sample.domain.entity.ShortBankEntity

interface SelectBankView : LoadDataView, MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderBankList(bankEntityList: List<ShortBankEntity>)

    @StateStrategyType(SkipStrategy::class)
    fun viewBankDetails(bankEntity: ShortBankEntity)

}