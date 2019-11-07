package ru.sample.presentation.view.interfaces

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.sample.domain.entity.ShortBankEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface SelectBankView : LoadDataView, MvpView {
    fun renderBankList(bankEntityList: List<ShortBankEntity>)
    fun viewBankDetails(bankEntity: ShortBankEntity)
}