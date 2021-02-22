package uz.triples.qulaymarket.product

import android.os.Bundle
import android.view.View
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
import uz.triples.qulaymarket.product.adapter.DetailsRVA
import uz.triples.qulaymarket.product.adapter.ImageVPA

class ProductFragment : Fragment(R.layout.fragment_product) {

    private lateinit var adapter: GoodsRVA
    private lateinit var adapterDetails: DetailsRVA
    private lateinit var adapterVP: ImageVPA
    private var listGoods: List<Goods> = listOf()
    private var listImage: List<Int> = listOf()
    private var listDetails: List<Details> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        unrealInsertion()

        adapter = GoodsRVA(requireContext(), object : ProductClicked {
            override fun itemClicked() {
                findNavController().navigate(R.id.productFragment)
            }
        }, true)
        adapterVP = ImageVPA(listImage)
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
    }

    private fun unrealInsertion() {
        listDetails = listDetails + Details("Shahar: ", "Toshkent")
        listDetails = listDetails + Details("Telefon markasi: ", "Xiaomi")
        listDetails = listDetails + Details("Rangi: ", "Qora")
        listDetails = listDetails + Details("Holati: ", "Ishlatilgan")
        listDetails = listDetails + Details("Sotuvchi: ", "Jismoniy shaxs")

        listImage = listImage + R.drawable.test_img6
        listImage = listImage + R.drawable.test_img6
        listImage = listImage + R.drawable.test_img6
        listImage = listImage + R.drawable.test_img6

        listGoods = listGoods + Goods(
            "7 fev",
            135000,
            Author("+998916654355", "1 fev", "Sokhibjon", "Toshkent", 1),
            "sum",
            "Toshkent shahar, Mirzo Ulug’bek tumani",
            R.drawable.test_img2,
            "Уголок в стиле хай-тек  универсал",
            124
        )
        listGoods = listGoods + Goods(
            "9 fev",
            135000,
            Author("+998886618866", "5 fev", "Alijon", "Andijon", 1),
            "sum",
            "Toshkent shahar, Mirzo Ulug’bek tumani",
            R.drawable.test_img3,
            "Уголок в стиле хай-тек  универсал",
            14
        )
        listGoods = listGoods + Goods(
            "7 fev",
            3254000,
            Author("+998916654355", "1 fev", "Sokhibjon", "Toshkent", 1),
            "sum",
            "Toshkent shahar, Mirzo Ulug’bek tumani",
            R.drawable.test_img1,
            "Уголок в стиле хай-тек  универсал",
            124
        )
        listGoods = listGoods + Goods(
            "7 fev",
            135000,
            Author("+998916654355", "1 fev", "Sokhibjon", "Toshkent", 1),
            "sum",
            "Toshkent shahar, Mirzo Ulug’bek tumani",
            R.drawable.test_img4,
            "Уголок в стиле хай-тек  универсал",
            124
        )
    }
}