package ru.sample.presentation.view.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.sample.domain.entity.ItemTariff
import ru.sample.presentation.R
import ru.sample.presentation.view.utils.Utils
import javax.inject.Inject

class TarrifsAdapter @Inject constructor(private val context: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tariffsCollection: MutableList<ItemTariff> = mutableListOf()
    private val layoutInflater: LayoutInflater = LayoutInflater.from(
        ContextThemeWrapper(context.applicationContext, R.style.AppTheme)
    )

    private val VIEW_TYPE_DEFAULT = 0
    private val VIEW_TYPE_URL = 1

    override fun getItemCount() = this.tariffsCollection.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_URL) {
            val view = this.layoutInflater.inflate(R.layout.item_url, parent, false)
            return URLViewHolder(view)
        } else {
            val view = this.layoutInflater.inflate(R.layout.item_tariff, parent, false)
            return TariffViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TariffViewHolder) {
            val tariffViewHolder = holder
            val itemTariff = tariffsCollection[position]
            tariffViewHolder.tv_name_tariff.setText(itemTariff.name)
            tariffViewHolder.tv_value_tariff.setText(itemTariff.value)
            if (position == tariffsCollection.size - 1) {
                tariffViewHolder.divider.visibility = View.INVISIBLE
            }
        } else if (holder is URLViewHolder) {
            val urlViewHolder = holder
            val itemTariff = tariffsCollection[position]
            urlViewHolder.url = itemTariff.url
            if (itemTariff.textUrl.isEmpty()) {
                urlViewHolder.btn_url.setText(itemTariff.textUrl)
            } else {
                urlViewHolder.btn_url.setText(Utils.getString(R.string.ext_tariffs))
            }

            urlViewHolder.btn_url.setOnClickListener {
                try {
                    val url = itemTariff.url
                    val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
                    context.startActivity(intent)
                } catch (e: Throwable) {
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val itemTariff = tariffsCollection[position]
        return if (itemTariff.isUrl) VIEW_TYPE_URL else VIEW_TYPE_DEFAULT
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setTariffsCollection(tariffsCollection: List<ItemTariff>) {
        val diffResult =
            DiffUtil.calculateDiff(AssetDiffUtil(this.tariffsCollection, tariffsCollection))
        this.tariffsCollection.clear()
        this.tariffsCollection.addAll(tariffsCollection)
        diffResult.dispatchUpdatesTo(this)
    }

    class TariffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv_name_tariff: TextView

        var tv_value_tariff: TextView

        var divider: View

        init {
            tv_name_tariff = itemView.findViewById(R.id.tv_name_tariff)
            tv_value_tariff = itemView.findViewById(R.id.tv_value_tariff)
            divider = itemView.findViewById(R.id.divider_header)
        }
    }

    class URLViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var btn_url: Button
        var url: String? = null

        init {
            btn_url = itemView.findViewById(R.id.btn_url)
            btn_url.paintFlags = btn_url.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

    private class AssetDiffUtil(
        private val oldList: List<ItemTariff>,
        private val newList: List<ItemTariff>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize()= newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldTariff = oldList[oldItemPosition]
            val newTariff = newList[newItemPosition]
            val nameOld = oldTariff.name
            val nameNew = newTariff.name
            if (!nameOld.isEmpty() && !nameNew.isEmpty()) {
                return nameOld == nameNew
            }
            val oldTextUrl = oldTariff.textUrl
            val newTextUrl = newTariff.textUrl
            if(!oldTextUrl.isEmpty() && !newTextUrl.isEmpty()) return oldTextUrl == newTextUrl
            else return false
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldTariff = oldList[oldItemPosition]
            val newTariff = newList[newItemPosition]
            val valueOld = oldTariff.value
            val valueNew = newTariff.value
            if (!valueOld.isEmpty() && !valueNew.isEmpty()) {
                return valueOld == valueNew
            }
            val oldUrl = oldTariff.url
            val newUrl = newTariff.url
            return if (!oldUrl.isEmpty() && !newUrl.isEmpty()) {
                oldUrl == newUrl
            } else false
        }
    }
}