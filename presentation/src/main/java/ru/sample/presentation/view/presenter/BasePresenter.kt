package ru.sample.presentation.view.presenter

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import ru.sample.presentation.AndroidApplication


open class BasePresenter<T: MvpView> : MvpPresenter<T>() {

    val component = AndroidApplication.application.applicationComponent

}