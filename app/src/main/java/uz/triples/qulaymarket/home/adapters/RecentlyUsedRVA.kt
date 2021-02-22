package uz.triples.qulaymarket.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_recently_searched.view.*
import uz.triples.qulaymarket.models.RecentlyUsed
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.`interface`.DeleteSearchedItem

class RecentlyUsedRVA(
    val context: Context,
    private val deleteItem: DeleteSearchedItem
) :
    ListAdapter<RecentlyUsed, RecentlyUsedRVA.VH>(DiffUtilCallBack()) {

    class DiffUtilCallBack : DiffUtil.ItemCallback<RecentlyUsed>() {
        override fun areItemsTheSame(
            oldItem: RecentlyUsed,
            newItem: RecentlyUsed
        ): Boolean {
            return true
        }

        override fun areContentsTheSame(
            oldItem: RecentlyUsed,
            newItem: RecentlyUsed
        ): Boolean {
            return oldItem.value == newItem.value
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(context)
                .inflate(R.layout.rv_recently_searched, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val itemView = holder.itemView
        val item = getItem(position)
        itemView.recentlyUsedOne.text = item.value
        itemView.recentlyUsedDelete.setOnClickListener {
            deleteItem.deleteItem(item.value)
        }
    }

}