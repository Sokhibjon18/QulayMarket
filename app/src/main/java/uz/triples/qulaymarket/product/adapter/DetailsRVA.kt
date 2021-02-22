package uz.triples.qulaymarket.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_product_info.view.*
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.models.Details

class DetailsRVA(val context: Context) :
    ListAdapter<Details, DetailsRVA.VH>(DiffUtilCallBack()) {

    class DiffUtilCallBack : DiffUtil.ItemCallback<Details>() {
        override fun areItemsTheSame(
            oldItem: Details,
            newItem: Details
        ): Boolean {
            return true
        }

        override fun areContentsTheSame(
            oldItem: Details,
            newItem: Details
        ): Boolean {
            return oldItem.detailType == newItem.detailType
                    && oldItem.detailInfo == newItem.detailInfo
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(context)
                .inflate(R.layout.rv_product_info, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val itemView = holder.itemView
        val item = getItem(position)
        itemView.detailInfo.text = item.detailInfo
        itemView.detailType.text = item.detailType
    }
}