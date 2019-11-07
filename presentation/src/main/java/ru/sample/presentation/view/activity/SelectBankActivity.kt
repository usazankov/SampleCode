package ru.sample.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import ru.sample.domain.entity.ShortBankEntity
import ru.sample.presentation.R
import ru.sample.presentation.internal.di.HasComponent
import ru.sample.presentation.internal.di.components.DaggerSoftPosComponent
import ru.sample.presentation.internal.di.components.SoftPosComponent
import ru.sample.presentation.view.fragment.SelectBankFragment

class SelectBankActivity : BaseActivity(), HasComponent<SoftPosComponent>,
    SelectBankFragment.BankListListener {

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, SelectBankActivity::class.java)
        }
    }

    override lateinit var component: SoftPosComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        initializeInjector()
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, SelectBankFragment())
        }
    }

    private fun initializeInjector() {
        component = DaggerSoftPosComponent.builder()
            .applicationComponent(applicationComponent)
            .activityModule(activityModule)
            .build()
    }

    override fun onBankClicked(bankModel: ShortBankEntity) {
//        replaceFragment(R.id.fragmentContainer, BankDetailsFragment.forBankId(bankModel.getId()))
    }
//
//    fun onIssueClick(bankId: Int) {
//        replaceFragment(R.id.fragmentContainer, ConditionsFragment.forBankId(bankId))
//    }
//
//    fun conditionsApply(bankId: Int) {
//        replaceFragment(R.id.fragmentContainer, RegistrationFragment.forBankId(bankId))
//    }

//    fun onRegClicked(bankId: Int) {
//        navigator.navigateToDocumentsActivity(this, bankId)
//    }
}