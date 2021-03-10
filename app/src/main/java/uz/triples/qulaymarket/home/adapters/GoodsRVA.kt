package uz.triples.qulaymarket.home.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_goods.view.*
import okhttp3.*
import uz.triples.qulaymarket.*
import uz.triples.qulaymarket.`interface`.ProductClicked
import uz.triples.qulaymarket.databinding.RvGoodsBinding
import uz.triples.qulaymarket.databinding.RvGoodsInRelatedBinding
import uz.triples.qulaymarket.network.Network.baseUrl
import uz.triples.qulaymarket.network.pojo_objects.Announcement
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class GoodsRVA(
    val context: Context,
    private val productClicked: ProductClicked,
    private val related: Boolean
) :
    ListAdapter<Announcement, GoodsRVA.VH>(DiffUtilCallBack()) {

    private var bindingRelated: RvGoodsInRelatedBinding? = null
    private var binding: RvGoodsBinding? = null

    class DiffUtilCallBack : DiffUtil.ItemCallback<Announcement>() {
        override fun areItemsTheSame(
            oldItem: Announcement,
            newItem: Announcement
        ): Boolean {
            return true
        }

        override fun areContentsTheSame(
            oldItem: Announcement,
            newItem: Announcement
        ): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.title == newItem.title
                    && oldItem.title == newItem.title
                    && oldItem.price == newItem.price
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return if (related){
            bindingRelated = RvGoodsInRelatedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            VH(bindingRelated!!.root)
        } else{
            binding = RvGoodsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            VH(binding!!.root)
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
//        Glide.with(context).load("$baseUrl/announcement/image?announcement_id=${item.id}&image_id=1").into(itemView.imageItemGoodsRV)
//        itemView.titleGoodsRV.text = item.title
//        itemView.priceGoodRV.text = item.price.toString()
//        itemView.locationGoodsRV.text = item.location?.valueUz
//        itemView.lastSeenGoodsRV.apply {
//            val date = convertLongToTime(item.datetime?.toLong()!!)
//            this.text = date
//        }

        if(related){
            TODO()
        } else{
            binding?.let {
                it.announcement = item

                it.root.setOnClickListener {
                    productClicked.itemClicked(getItem(position))
                }

                it.executePendingBindings()
            }
        }
    }

//    private fun convertLongToTime(time: Long): String {
//        val date = Date(time)
//        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
//        return format.format(date)
//    }

}