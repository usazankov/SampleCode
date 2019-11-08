package ru.sample.presentation.view.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ShortBankEntity
import ru.sample.domain.interactor.GetBankDetails
import ru.sample.domain.interactor.GetBankList
import ru.sample.presentation.view.interfaces.BankDetailsView
import javax.inject.Inject

@InjectViewState
class BankDetailsPresenter @Inject constructor() : BasePresenter<BankDetailsView>() {

    @Inject
    lateinit var getBankDetails: GetBankDetails

    @Inject
    lateinit var getBankList: GetBankList

    private val disposables: CompositeDisposable = CompositeDisposable()


    /**
     * Initializes the presenter.
     */
    fun initialize(bankId: Int) {
//        this.hideViewRetry()
//        this.showViewLoading()
//        getBankList.buildUseCaseObservable(null)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(GetBankListObserver())
//
//        val disposable = bankDetailsView!!.changeBankId()
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnNext(Consumer<Any> { stateBankId ->
//                if (!stateBankId.isChanged) {
//                    bankDetailsView!!.viewLoadBankDetails()
//                } else {
//                    bankDetailsView!!.hideLoadBankDetails()
//                }
//            })
//            .filter(Predicate<Any> { changeStateBankId -> changeStateBankId.isChanged })
//            .switchMap(Function<Any, ObservableSource<Any>> { changeStateBankId ->
//                getBankDetails.buildUseCaseObservable(
//                    GetBankDetails.Params.forBankId(
//                        changeStateBankId.id
//                    )
//                )
//                    .subscribeOn(Schedulers.io())
//            })
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(Consumer<Any> { fullBankEntity ->
//                bankDetailsView!!.hideLoadBankDetails()
//                bankDetailsView!!.renderBankDetails(fullBankEntity)
//            }, Consumer<Throwable> { throwable ->
//                bankDetailsView!!.hideLoadBankDetails()
//                showErrorMessage(throwable)
//            })
//        disposables.add(disposable)
    }

    fun initBankDetails(bankId: Int) {
//        getBankDetails.execute(GetDetailtBankObserver(), GetBankDetails.Params.forBankId(bankId))
//        //        Disposable disposable = getBankDetails.buildUseCaseObservable(GetBankDetails.Params.forBankId(bankId))
//        //                .lastElement()
//        //                .subscribeOn(Schedulers.io())
//        //                .observeOn(AndroidSchedulers.mainThread())
//        //                .subscribe(fullBankEntity -> bankDetailsView.renderBankDetails(fullBankEntity));

    }

    override fun onDestroy() {
        disposables.dispose()
        getBankDetails.dispose()
    }

    private fun showViewLoading() {
        viewState.showLoading()
    }

    private fun hideViewLoading() {
        viewState.hideLoading()
    }

    private fun showViewRetry(message: String) {
        viewState.showRetry(message)
    }

    private fun showViewRetry(throwable: Throwable) {
        //val message = ErrorMessageFactory.create(throwable)
        viewState.showRetry("Ошибка")
    }

    private fun hideViewRetry() {
        viewState.hideRetry()
    }

    private fun showErrorMessage(error: Throwable) {
//        val errorMessage = ErrorMessageFactory.create(
//            error
//        )
        viewState.showError("Ошибка")
    }

    private inner class GetBankListObserver : DisposableObserver<List<ShortBankEntity>>() {

        override fun onNext(bankEntity: List<ShortBankEntity>) {
            viewState.renderBankPager(bankEntity)
        }

        override fun onComplete() {
            hideViewLoading()
        }

        override fun onError(exception: Throwable) {
            hideViewLoading()
            showViewRetry(exception)
        }
    }

    private inner class GetDetailtBankObserver : DisposableObserver<FullBankEntity>() {

        override fun onNext(bankEntity: FullBankEntity) {
            viewState.renderBankDetails(bankEntity)
        }

        override fun onComplete() {
            hideViewLoading()
        }

        override fun onError(exception: Throwable) {
            hideViewLoading()
            showViewRetry(exception)
        }
    }
}