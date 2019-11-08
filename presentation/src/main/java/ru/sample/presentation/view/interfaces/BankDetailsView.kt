package ru.sample.presentation.view.interfaces

import com.arellomobile.mvp.MvpView
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ShortBankEntity


interface BankDetailsView : LoadDataView, MvpView{
    fun renderBankDetails(bankEntity: FullBankEntity)
    fun renderBankPager(bankEntityList: List<ShortBankEntity>)
    fun viewLoadBankDetails()
    fun hideLoadBankDetails()
}