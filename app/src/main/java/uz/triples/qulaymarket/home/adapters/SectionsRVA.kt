package uz.triples.qulaymarket.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_sections.view.*
import uz.triples.qulaymarket.models.Section
import uz.triples.qulaymarket.R

class SectionsRVA(val context: Context) :
    ListAdapter<Section, SectionsRVA.VH>(DiffUtilCallBack()) {

    class DiffUtilCallBack : DiffUtil.ItemCallback<Section>() {
        override fun areItemsTheSame(
            oldItem: Section,
            newItem: Section
        ): Boolean {
            return true
        }

        override fun areContentsTheSame(
            oldItem: Section,
            newItem: Section
        ): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(context)
                .inflate(R.layout.rv_sections, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val itemView = holder.itemView
        itemView.imageSectionRV.setImageResource(R.drawable.test_ic)
        itemView.titleSectionRV.text = getItem(position).title
        if (getItem(position).selected) {
            itemView.imageSectionRV.background = ResourcesCompat.getDrawable(
                context.resources,
                R.drawable.sections_selected_bkg,
                null
            )
        }
        else {
            itemView.imageSectionRV.background = ResourcesCompat.getDrawable(
                context.resources,
                R.drawable.sections_unselected_bkg,
                null
            )
        }
    }

}