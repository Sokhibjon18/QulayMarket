package uz.triples.qulaymarket.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_goods.view.*
import uz.triples.qulaymarket.models.Goods
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.`interface`.ProductClicked

class GoodsRVA(
    val context: Context,
    private val productClicked: ProductClicked,
    private val related: Boolean
) :
    ListAdapter<Goods, GoodsRVA.VH>(DiffUtilCallBack()) {

    class DiffUtilCallBack : DiffUtil.ItemCallback<Goods>() {
        override fun areItemsTheSame(
            oldItem: Goods,
            newItem: Goods
        ): Boolean {
            return true
        }

        override fun areContentsTheSame(
            oldItem: Goods,
            newItem: Goods
        ): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.title == newItem.title
                    && oldItem.title == newItem.title
                    && oldItem.price == newItem.price
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return if (related)
            VH(
                LayoutInflater.from(context)
                    .inflate(R.layout.rv_goods_in_related, parent, false)
            )
            else
            VH(
                LayoutInflater.from(context)
                    .inflate(R.layout.rv_goods, parent, false)
            )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val itemView = holder.itemView
        val item = getItem(position)
        itemView.imageItemGoodsRV.setImageResource(item.id!!)
        itemView.titleGoodsRV.text = item.title
        itemView.priceGoodRV.text = item.price.toString()
        itemView.locationGoodsRV.text = item.location
        itemView.lastSeenGoodsRV.text = item.date

        itemView.setOnClickListener {
            productClicked.itemClicked()
        }
    }

}