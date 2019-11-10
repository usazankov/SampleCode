package ru.sample.presentation.view.fragment

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_bank_details.*
import ru.sample.domain.entity.FullBankEntity
import ru.sample.domain.entity.ItemTariff
import ru.sample.domain.entity.ShortBankEntity
import ru.sample.presentation.R
import ru.sample.presentation.internal.di.components.SoftPosComponent
import ru.sample.presentation.view.adapter.BanksPagerAdapter
import ru.sample.presentation.view.gradationpager.GradationViewPager
import ru.sample.presentation.view.presenter.BankDetailsPresenter
import javax.inject.Inject
import kotlinx.android.synthetic.main.view_progress_small.*
import ru.sample.presentation.view.adapter.TarrifsAdapter
import ru.sample.presentation.view.interfaces.BankDetailsView
import ru.sample.presentation.view.utils.UIParam
import ru.sample.presentation.view.utils.Utils
import java.util.concurrent.TimeUnit

class BankDetailsFragment : BaseFragment(), BankDetailsView{

    companion object {
        fun forBankId(bankId: Int): BankDetailsFragment {
            val args = Bundle()
            args.putInt(UIParam.STATE_PARAM_BANK_ID, bankId)
            val bankDetailsFragment = BankDetailsFragment()
            bankDetailsFragment.arguments = args
            return bankDetailsFragment
        }
    }

    interface ButtonListener {
        fun onIssueClick(bankId: Int)
    }

    private var buttonListener: ButtonListener? = null

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var bankDetailsPresenter: BankDetailsPresenter

    @Inject
    lateinit var banksPagerAdapter: BanksPagerAdapter

    @Inject
    lateinit var tarrifsAdapter: TarrifsAdapter

    val disposables = CompositeDisposable()

    override fun onClickRetry() {
        bankDetailsPresenter.initialize(currentBankId())
    }

    init {
        retainInstance = true
    }

    val selectBankListener = View.OnClickListener{
        if (buttonListener != null) {
            val shortBankEntity =
                gradationViewPager.getByPosition(gradationViewPager.getCurrentItem() - 1)
            val args = Bundle()
            if(shortBankEntity != null){
                args.putInt(UIParam.STATE_PARAM_BANK_ID, shortBankEntity.id)
                setArguments(args)
                buttonListener?.onIssueClick(shortBankEntity.id)
            }
        }
    }

    private fun initListeners(){
        val d = Observable.create<Int> {
            val pagerListener = object : GradationViewPager.OnChangePageListener {
                override fun onChangePage(position: Int) {
                    val shortBankEntity = gradationViewPager.getByPosition(position)
                    if (shortBankEntity != null) {
                        it.onNext(shortBankEntity.id)
                    }
                }
            }
            gradationViewPager.setChangePageListener(pagerListener)
        }
            .distinctUntilChanged()
            .doOnNext{viewLoadBankDetails()}
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe{
                bankDetailsPresenter.initBankDetails(it)
            }
        disposables.add(d)
    }

    override fun renderBankDetails(bankEntity: FullBankEntity) {
        val tariffs = bankEntity.tariffs
        if (!bankEntity.urlFullTariffs.isEmpty()) {
            try {
                val itemTariff = ItemTariff()
                itemTariff.isUrl = true
                itemTariff.url = bankEntity.urlFullTariffs
                itemTariff.textUrl = bankEntity.textFullTariffs
                tariffs.add(itemTariff)
            } catch (e: Exception) {
            }
        }
        tarrifsAdapter.setTariffsCollection(tariffs)
        val layoutManager = rv_tariffs.getLayoutManager() as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(0, 0)
    }

    override fun renderBankPager(bankEntityList: List<ShortBankEntity>) {
        if (!bankEntityList.isEmpty()) {
            val colors = IntArray(bankEntityList.size)
            var currentPos = 1
            val bankId = currentBankId()
            for (i in bankEntityList.indices) {
                val shortBankEntity = bankEntityList[i]
                if (shortBankEntity.id.equals(bankId)) {
                    currentPos = i + 1
                }
                try {
                    colors[i] = Color.parseColor(if(shortBankEntity.color.length > 1) shortBankEntity.color else "FFFFFF")
                } catch (e: Exception) {
                    colors[i] = 0xFFFFFF
                }
            }
            gradationViewPager.setBackGroundColors(colors)
            banksPagerAdapter.setBanksList(bankEntityList)
            gradationViewPager.setAdapter(banksPagerAdapter)
            gradationViewPager.setCurrentItem(currentPos)
            tabBanks.createTab(gradationViewPager, banksPagerAdapter, currentPos - 1)
        }
    }

    override fun viewLoadBankDetails() {
        rl_progress_small?.visibility = View.VISIBLE
    }

    override fun hideLoadBankDetails() {
        rl_progress_small?.visibility = View.GONE
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity is ButtonListener) {
            buttonListener = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(SoftPosComponent::class.java)?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentView = inflater.inflate(R.layout.fragment_bank_details, container, false)
        initToolBar(fragmentView, inflater)
        setTitleToolBar(R.string.title_list_banks)
        return fragmentView
    }

    private fun setupViews() {
        initListeners()
        btn_select_bank.setOnClickListener(selectBankListener)
        val window = getActivity()?.getWindow()
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        appBar.setZ(0.1f)
        val layoutParamsAppBar = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParamsAppBar.topMargin = Utils.getStatusBarHeight()
        appBar.setLayoutParams(layoutParamsAppBar)
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.bottomMargin = Utils.getNavigationBarHeight()
        rl_bank_details.setLayoutParams(layoutParams)
        val m = context().getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        if (m != null) {
            val metrics = DisplayMetrics()
            m.defaultDisplay.getMetrics(metrics)
            val heightDp = Utils.pxToDp(metrics.heightPixels.toFloat())
            val layoutParamsPager = FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                Utils.dpToPx((heightDp/2).toInt())
            )
            rl_pager.layoutParams = layoutParamsPager
        }

        val viewTreeObserver = fl_main.viewTreeObserver
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                fl_main.viewTreeObserver.removeGlobalOnLayoutListener(this)
                val height_fl = fl_main.measuredHeight
                val height_pager = gradationViewPager?.getMeasuredHeight() ?: 0
                val delta = Utils.dpToPx(20)
                val layoutParamsTariffs = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    height_fl - height_pager + delta
                )
                layoutParamsTariffs.gravity = Gravity.BOTTOM
                rl_tariffs.layoutParams = layoutParamsTariffs
            }
        })
        rv_tariffs.setLayoutManager(LinearLayoutManager(context()))
        rv_tariffs.setAdapter(tarrifsAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        loadBank()
    }

    /**
     * Get current bank id from fragments arguments.
     */
    private fun currentBankId(): Int {
        val id = getArguments()?.getInt(UIParam.STATE_PARAM_BANK_ID)
        return if (id == null) -1 else id
    }

    private fun loadBank() {
        bankDetailsPresenter.initialize(currentBankId())
    }

    override fun showRetry(message: String) {
        //showErrorPopupRtry(message, null)
    }

    override fun hideRetry() {
        //hideErrorPopupRetry()
    }

    override fun showError(message: String) {
        //showErrorPopup(message, null)
    }

    override fun showLoading() {
        showProgressBarLoading()
    }

    override fun hideLoading() {
        hideProgressBarLoading()
    }

    fun context(): Context {
        return this.getActivity()!!
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
        getActivity()?.getWindow()?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(UIParam.STATE_PARAM_BANK_ID, currentBankId())
    }

}
