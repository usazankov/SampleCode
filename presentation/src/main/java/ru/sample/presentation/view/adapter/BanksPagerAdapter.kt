package ru.sample.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import ru.sample.domain.entity.ShortBankEntity
import ru.sample.presentation.R
import javax.inject.Inject

class BanksPagerAdapter @Inject constructor(private val context: Context, private val picasso: Picasso) : PagerAdapter() {

    private var list: List<ShortBankEntity>? = null
    private val layoutInflater: LayoutInflater

    init {
        this.layoutInflater =
            LayoutInflater.from(ContextThemeWrapper(context.applicationContext, R.style.AppTheme))
    }

    fun setBanksList(list: List<ShortBankEntity>) {
        this.list = list
    }

    override fun getCount(): Int = if (list == null) 0 else list!!.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = layoutInflater.inflate(R.layout.item_pager_bank, null) as ViewGroup
        val imageView = layout.findViewById(R.id.iv_item_pager) as ImageView
        val bankModel = list!![position]

        picasso.load(bankModel.coverUrl)
            .centerInside()
            .fit()
            .into(imageView)

        container.addView(layout)
        return layout
    }

    fun getByPosition(position: Int): ShortBankEntity? {
        return if (list != null) list!![position] else null
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    override fun getPageTitle(position: Int): CharSequence {
        return list!![position].shortName
    }
}