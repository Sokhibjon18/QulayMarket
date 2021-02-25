package uz.triples.qulaymarket.add_announcement

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AddMediaAdapter: RecyclerView.Adapter<AddMediaAdapter.MyViewHolder>() {

    private val data = ArrayList<MediaModel>()

    fun addMedia(model: MediaModel){
        data.add(model)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        TODO()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount() = data.size
}