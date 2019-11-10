package ru.sample.presentation.view.gradationpager

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import ru.sample.domain.entity.ShortBankEntity
import ru.sample.presentation.view.adapter.BanksPagerAdapter

class GradationViewPager : ViewPager {

    private var colors: IntArray? = intArrayOf()
    private var mAdapter: GradationPagerAdapter? = null
    private var pagerAdapter: PagerAdapter? = null

    private var changePageListener: OnChangePageListener? = null

    val backGroundColors: Int
        get() = if (colors == null) 0 else colors!!.size

    private val mOnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            if (mAdapter != null && colors != null && colors!!.size > 0) {
                val nextColor = colors!![(mAdapter!!.getIndex(position) + 1) % colors!!.size]
                val currentColor = colors!![mAdapter!!.getIndex(position) % colors!!.size]
                val currentRed = (currentColor shr 16 and 0xff).toFloat()
                val currentGreen = (currentColor shr 8 and 0xff).toFloat()
                val currentBlue = (currentColor and 0xff).toFloat()
                val nextRed = (nextColor shr 16 and 0xff).toFloat()
                val nextGreen = (nextColor shr 8 and 0xff).toFloat()
                val nextBlue = (nextColor and 0xff).toFloat()

                val newRed = (currentRed + (nextRed - currentRed) * positionOffset).toInt()
                val newGreen = (currentGreen + (nextGreen - currentGreen) * positionOffset).toInt()
                val newBlue = (currentBlue + (nextBlue - currentBlue) * positionOffset).toInt()
                val newColor = Color.rgb(newRed, newGreen, newBlue)
                setBackgroundColor(newColor)
            }
        }

        override fun onPageSelected(position: Int) {
            if (changePageListener != null) {
                changePageListener!!.onChangePage(mAdapter!!.getIndex(position))
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
            if (state == SCROLL_STATE_IDLE) {
                val position = getCurrentItem()
                if (position == 0) {
                    setCurrentItem(colors!!.size, false)
                } else if (position == getAdapter()!!.getCount() - 1) {
                    setCurrentItem(1, false)
                }
            }
        }
    }

    interface OnChangePageListener {
        fun onChangePage(position: Int)
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    fun setChangePageListener(changePageListener: OnChangePageListener) {
        this.changePageListener = changePageListener
    }

    private fun init() {
        addOnPageChangeListener(mOnPageChangeListener)
    }

    fun setBackGroundColors(colors: IntArray) {
        this.colors = colors
        setBackgroundColor(colors[colors.size - 1])
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        pagerAdapter = adapter
        if(adapter != null) mAdapter = GradationPagerAdapter(adapter)
        super.setAdapter(mAdapter)
    }

    fun getByPosition(position: Int): ShortBankEntity? {
        return if (pagerAdapter is BanksPagerAdapter) {
            (pagerAdapter as BanksPagerAdapter).getByPosition(mAdapter!!.getIndex(position + 1))
        } else null
    }
}