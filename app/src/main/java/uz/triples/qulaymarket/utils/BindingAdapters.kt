package uz.triples.qulaymarket.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import de.hdodenhof.circleimageview.CircleImageView
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.home.adapters.GoodsRVA
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.Network.baseUrl
import uz.triples.qulaymarket.network.pojo_objects.Announcement
import uz.triples.qulaymarket.network.pojo_objects.User
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("listData")
fun bindRecyclerViewAnnouncements(recyclerView: RecyclerView, data: List<Announcement>?){
    val adapter = recyclerView.adapter as GoodsRVA
    data?.let {
        adapter.submitList(it)
    }
}

@BindingAdapter("lastSeenGoodsRv")
fun lastSeenGoodsRv(textView: TextView, announcement: Announcement){
    val date = convertLongToTime(announcement.datetime?.toLong()!!)
    textView.text = date
}

@BindingAdapter("imageOfTheProduct")
fun setImageOfTheProduct(imageView: ShapeableImageView, announcement: Announcement){
    Glide.with(imageView.context).load("${Network.baseUrl}/announcement/image?announcement_id=${announcement.id}&image_id=1").into(imageView)
}

@BindingAdapter("writeCost")
fun writeCost(textView: TextView, announcement: Announcement){
    val cost = "${announcement.price} so'm"
    textView.text = cost
}

private fun convertLongToTime(time: Long): String {
    val date = Date(time*1000)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
    return format.format(date)
}

@BindingAdapter("setUserName")
fun setUserName(textView: TextView, user: User?){
    val it = user ?: Cache.getUserDetails()

    if(it.name != "null"){
        textView.text = it.name
    }
}

@BindingAdapter("setUserId")
fun setUserId(textView: TextView, user: User?) {
    val it = user ?: Cache.getUserDetails()

    if(it.id != -1){
        val txt = "ID: ${it.id}"
        textView.text = txt
    }
}

@BindingAdapter("setUserEmail")
fun setUserEmail(textView: TextView, user: User?){
    val it = user ?: Cache.getUserDetails()
    if(it.email != "null") textView.text = it.email
}

@BindingAdapter("setUserPhone")
fun setUserPhone(textView: TextView, user: User?) {
    val it = user ?: Cache.getUserDetails()

    if(it.phone != "null"){
        val txt = "(998)${it.phone?.substring(0, 1)} ${it.phone?.substring(2, 4)}-${it.phone?.substring(5, 6)}-${it.phone?.substring(7, 8)}"
        textView.text = txt
    }
}

@BindingAdapter("setUserBirthDate")
fun setUserBirthDate(textView: TextView, user: User?){
    val it = user ?: Cache.getUserDetails()

    if(it.birthdate != "null") textView.text = convertLongToDate(it.birthdate!!.toLong())
}

private fun convertLongToDate(time: Long): String {
    val date = Date(time*1000)
    val format = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)
    return format.format(date)
}

@BindingAdapter("setUserImage")
fun setUserImage(view: CircleImageView, user: User?){
    user?.let {
        Glide.with(view.context).load("$baseUrl/user/image?user_id=${it.id}")
            .placeholder(R.drawable.ic_man_user)
            .into(view)
    }
}