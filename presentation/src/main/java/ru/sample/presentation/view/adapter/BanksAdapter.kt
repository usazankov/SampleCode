package ru.sample.presentation.view.adapter

import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.sample.domain.entity.ShortBankEntity
import ru.sample.presentation.R
import ru.sample.presentation.view.utils.Utils
import javax.inject.Inject

/**
 * Adaptar that manages a collection of [ru.sample.domain.entity.ShortBankEntity].
 */
class BanksAdapter @Inject constructor(private val context: Context, private val picasso: Picasso) :
    RecyclerView.Adapter<BanksAdapter.BankViewHolder>() {

    override fun getItemCount(): Int = this.banksCollection.size

    private var banksCollection: List<ShortBankEntity> = emptyList()
    private val layoutInflater: LayoutInflater = LayoutInflater.from(ContextThemeWrapper(context.applicationContext, R.style.AppTheme))

    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onBankItemClicked(userModel: ShortBankEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        val view = this.layoutInflater.inflate(R.layout.item_bank, parent, false)
        return BankViewHolder(view)
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        val bankModel = this.banksCollection[position]
        setupView(holder)
        try {
            val color = bankModel.color
            holder.cardBank.setCardBackgroundColor(Color.parseColor(if(color.length >= 1) color else "000000"))
        } catch (e: Exception) {
        }

        picasso.load(bankModel.coverUrl)
            .centerInside()
            .fit()
            .into(holder.imageBank)

        holder.cardBank.setOnClickListener {
            onItemClickListener?.onBankItemClicked(bankModel)
        }
    }

    private fun setupView(holder: BankViewHolder) {
        val m = context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        if (m != null) {
            val metrics = DisplayMetrics()
            m.defaultDisplay.getMetrics(metrics)
            val heightDp = Utils.pxToDp(metrics.heightPixels.toFloat())
            val heightCard = heightDp / 6
            val layoutParams = holder.cardBank.layoutParams
            layoutParams.height = Utils.dpToPx(heightCard.toInt())
            holder.cardBank.layoutParams = layoutParams
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setBanksCollection(banksCollection: List<ShortBankEntity>) {
        this.banksCollection = banksCollection
        this.notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class BankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardBank = itemView.findViewById(R.id.cardBank) as CardView
        val imageBank = itemView.findViewById(R.id.image_bank) as ImageView

    }
}