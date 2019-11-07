package ru.sample.presentation.view.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter

import javax.inject.Inject

import ru.sample.domain.entity.ShortBankEntity
import ru.sample.presentation.R
import ru.sample.presentation.internal.di.components.SoftPosComponent
import ru.sample.presentation.view.adapter.BanksAdapter
import ru.sample.presentation.view.adapter.BanksLayoutManager
import ru.sample.presentation.view.adapter.GridSpacingItemDecoration
import ru.sample.presentation.view.interfaces.SelectBankView
import ru.sample.presentation.view.presenter.SelectBankPresenter

class SelectBankFragment : BaseFragment(), SelectBankView {

    private var bankListListener: BankListListener? = null

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var selectBankPresenter: SelectBankPresenter

    @Inject
    lateinit var banksAdapter: BanksAdapter

    private lateinit var rv_banks: RecyclerView

    private val itemClickListener = object : BanksAdapter.OnItemClickListener {
        override fun onBankItemClicked(userModel: ShortBankEntity) {
//            selectBankPresenter!!.onUserClicked(userModel)
        }
    }

    init {
        retainInstance = true
    }

    interface BankListListener {
        fun onBankClicked(bankModel: ShortBankEntity)
    }

    override fun onClickRetry() {
        //selectBankPresenter!!.initialize()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.bankListListener = activity as? BankListListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(SoftPosComponent::class.java)!!.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentView = inflater.inflate(R.layout.fragment_select_bank, container, false)
        setupViews(fragmentView)
        initToolBar(fragmentView, inflater)
        initProgressBar(fragmentView)
        setTitleToolBar(R.string.title_list_banks)
        setMainTitle(R.string.prompt_select_bank)

        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        selectBankPresenter.initialize()
    }

    override fun renderBankList(bankEntityList: List<ShortBankEntity>) {
        banksAdapter.setBanksCollection(bankEntityList)
    }

    private fun setupViews(view: View) {
        rv_banks = view.findViewById(R.id.rv_banks)
        banksAdapter.setOnItemClickListener(itemClickListener)
        val spanCount = 2
        val spacing = 12
        val includeEdge = false
        this.rv_banks.setLayoutManager(BanksLayoutManager(context(), spanCount))
        this.rv_banks.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount,
                spacing,
                includeEdge
            )
        )
        this.rv_banks.setAdapter(banksAdapter)
    }

    override fun viewBankDetails(bankEntity: ShortBankEntity) {
        bankListListener?.onBankClicked(bankEntity)
    }

    override fun showLoading() {
        rl_progress?.setVisibility(View.VISIBLE)
    }

    override fun hideLoading() {
        rl_progress?.setVisibility(View.GONE)
    }

    override fun showRetry(message: String) {
        //showErrorPopupRtry(message)
    }

    override fun hideRetry() {
        //hideErrorPopupRetry()
    }

    override fun showError(message: String) {
        //showErrorPopup(message, null)
    }

    fun context(): Context {
        return this.activity!!.getApplicationContext()
    }

}