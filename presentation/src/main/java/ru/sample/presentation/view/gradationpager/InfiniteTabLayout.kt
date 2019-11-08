package ru.sample.presentation.view.gradationpager

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class InfiniteTabLayout : TabLayout {

    private var mViewPager: ViewPager? = null
    private var isForce = false
    private var isOutBounds = false

    private val mOnTabSelectedListener = object : OnTabSelectedListener {
        override fun onTabSelected(tab: Tab) {
            if (mViewPager == null) {
                throw NullPointerException("viewpager is null")
            }
            if (isForce) {
                isForce = false
                return
            }
            mViewPager!!.postDelayed(Runnable {
                val currentItem = mViewPager!!.getCurrentItem()
                if (currentItem != tab.getPosition() + 1) {
                    isForce = true
                    mViewPager!!.setCurrentItem(tab.getPosition() + 1, true)
                }
            }, 30)
        }

        override fun onTabUnselected(tab: Tab) {

        }

        override fun onTabReselected(tab: Tab) {

        }
    }

    private val mOnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            if (mViewPager!!.getAdapter() == null) {
                throw NullPointerException("viewpager must attach pager adapter")
            }
            if (isForce) {
                isForce = false
                return
            }
            if (isOutBounds) {
                isOutBounds = false
                return
            }
            if (mViewPager!!.getAdapter() is GradationPagerAdapter && getTabCount() > 0) {
                isOutBounds = position == 0 || position == mViewPager!!.getAdapter()!!.getCount() - 1
                forceSelectTab(
                    (mViewPager!!.getAdapter() as GradationPagerAdapter).getIndex(
                        position
                    )
                )
            }
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    fun createTab(viewPager: ViewPager?, adapter: PagerAdapter?, currentPos: Int) {
        if (viewPager == null) {
            throw NullPointerException("viewpager is null")
        } else if (adapter == null) {
            throw NullPointerException("pager adapter is null")
        }
        for (i in 0 until adapter.getCount()) {
            addTab(newTab().setText(adapter.getPageTitle(i)))
        }
        mViewPager = viewPager
        mViewPager!!.addOnPageChangeListener(mOnPageChangeListener)
        addOnTabSelectedListener(mOnTabSelectedListener)
        getTabAt(currentPos)?.select()
    }

    private fun forceSelectTab(position: Int) {
        if (getTabAt(position) == null) {
            throw NullPointerException(String.format("getTabAt(%d) is null", position))
        }
        isForce = true
        getTabAt(position)?.select()
    }
}