package ru.sample.presentation.view.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.observers.DisposableObserver
import ru.sample.domain.entity.ShortBankEntity
import ru.sample.domain.interactor.GetBankList
import ru.sample.presentation.view.interfaces.SelectBankView
import javax.inject.Inject

@InjectViewState
class SelectBankPresenter : BasePresenter<SelectBankView>() {

    @Inject
    lateinit var getBankList: GetBankList

    init {
        component.inject(this)
    }

    /**
     * Initializes the presenter.
     */
    fun initialize() {
        loadBankList()
    }

    /**
     * Loads all users.
     */
    private fun loadBankList() {
        this.hideViewRetry()
        this.showViewLoading()
        getBankList.execute(GetBankListObserver(), null)
    }

    private fun hideViewRetry() {
        viewState.hideRetry()
    }

    private fun showViewLoading() {
        viewState.showLoading()
    }

    private fun hideViewLoading() {
        viewState.hideLoading()
    }

    fun selectBank(shortBankEntity: ShortBankEntity){
        viewState.viewBankDetails(shortBankEntity)
    }

    private fun showViewRetry(throwable: Throwable) {
        //val message = ErrorMessageFactory.create(throwable)
        viewState.showRetry("Ошибка")
    }

    override fun onDestroy() {
        super.onDestroy()
        getBankList.dispose()
    }

    private inner class GetBankListObserver : DisposableObserver<List<ShortBankEntity>>() {

        override fun onComplete() {
            hideViewLoading()
        }

        override fun onError(e: Throwable) {
            hideViewLoading()
            showViewRetry(e)
        }

        override fun  onNext(bankList: List<ShortBankEntity>) {
            viewState.renderBankList(bankList)
        }
    }
}