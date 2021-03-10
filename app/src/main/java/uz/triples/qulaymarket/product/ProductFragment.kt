package uz.triples.qulaymarket.product

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_product.*
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.`interface`.ProductClicked
import uz.triples.qulaymarket.home.adapters.GoodsRVA
import uz.triples.qulaymarket.models.Author
import uz.triples.qulaymarket.models.Details
import uz.triples.qulaymarket.models.Goods
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.pojo_objects.Announcement
import uz.triples.qulaymarket.product.adapter.DetailsRVA
import uz.triples.qulaymarket.product.adapter.ImageVPA

class ProductFragment : Fragment(R.layout.fragment_product) {

    private lateinit var adapter: GoodsRVA
    private lateinit var adapterDetails: DetailsRVA
    private lateinit var adapterVP: ImageVPA
    private var listGoods: List<Announcement> = listOf()
    private var listImage: List<String> = listOf()
    private var listDetails: List<Details> = listOf()
    private lateinit var announcement: Announcement

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        announcement = requireArguments().getParcelable("announcement")!!

        loadData()

        adapter = GoodsRVA(requireContext(), object : ProductClicked {
            override fun itemClicked(announcement: Announcement) {
                findNavController().navigate(R.id.productFragment, bundleOf("announcement" to announcement))
            }
        }, true)
        adapterVP = ImageVPA(requireContext(), listImage)
        adapterDetails = DetailsRVA(requireContext())

        detailsRVProduct.layoutManager = LinearLayoutManager(requireContext())
        detailsRVProduct.adapter = adapterDetails

        relatedProductsRV.adapter = adapter
        relatedProductsRV.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL,
            false
        )

        viewPagerProduct.adapter = adapterVP
        indicatorProduct.setViewPager(viewPagerProduct)
        viewPagerProduct.adapter?.registerDataSetObserver(indicatorProduct.dataSetObserver)

        adapter.submitList(listGoods)
        adapterDetails.submitList(listDetails)

        back_button.setOnClickListener{
            findNavController().navigate(R.id.action_productFragment_to_mainFragment)
        }
    }

    private fun loadData(){
        for (i in 0 until (announcement.imagesCount ?: 0)){
            listImage = listImage + "${Network.baseUrl}/announcement/image?announcement_id=${announcement.id}&image_id=${i+1}"
        }
        val views = "${announcement.views}"
        viewsCount.text = views

        val id = "ID: ${announcement.id}"
        productIDProduct.text = id

        val price = "${announcement.price?.toInt() ?: ""} so'm"
        priceProduct.text = price

        val title = "${announcement.title}"
        titleProduct.text = title
        productTitle.text = title

        val description = "${announcement.description}"
        descriptionProduct.text = description

        if(announcement.owner == null){
            ownerContainer.visibility = View.GONE
        } else{
            ownerContainer.visibility = View.VISIBLE
            userNameProduct.text = announcement.owner!!.name
            locationProducts.text = announcement.location?.valueUz
        }


    }
}