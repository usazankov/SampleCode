package ru.sample.presentation.view.gradationpager

import android.database.DataSetObserver
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class GradationPagerAdapter(private val mAdapter: PagerAdapter) : PagerAdapter() {

    private val originCount: Int
        get() = mAdapter.getCount()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return mAdapter.instantiateItem(container, getIndex(position))
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun getIndex(position: Int): Int {
        return if (position == 0) {
            originCount - 1
        } else if (position == count - 1) {
            0
        } else {
            position - 1
        }
    }

    override fun getCount(): Int = mAdapter.getCount() + 2

    override fun getPageTitle(position: Int): CharSequence? {
        return mAdapter.getPageTitle(getIndex(position))
    }

    override fun getPageWidth(position: Int): Float {
        return mAdapter.getPageWidth(getIndex(position))
    }

    override fun getItemPosition(`object`: Any): Int {
        return mAdapter.getItemPosition(`object`)
    }

    override fun saveState(): Parcelable? {
        return mAdapter.saveState()
    }

    override fun finishUpdate(container: ViewGroup) {
        mAdapter.finishUpdate(container)
    }

    override fun notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged()
    }

    override fun registerDataSetObserver(observer: DataSetObserver) {
        mAdapter.registerDataSetObserver(observer)
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
        mAdapter.restoreState(state, loader)
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        mAdapter.setPrimaryItem(container, getIndex(position), `object`)
    }

    override fun startUpdate(container: ViewGroup) {
        mAdapter.startUpdate(container)
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver) {
        mAdapter.unregisterDataSetObserver(observer)
    }

}