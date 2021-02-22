package uz.triples.qulaymarket.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_all_goods.*
import uz.triples.qulaymarket.models.Author
import uz.triples.qulaymarket.models.Goods
import uz.triples.qulaymarket.models.Section
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.`interface`.ProductClicked
import uz.triples.qulaymarket.home.adapters.GoodsRVA
import uz.triples.qulaymarket.home.adapters.SectionsRVA

class AllGoodsFragment : Fragment(R.layout.fragment_all_goods) {

    private val adapterSections: SectionsRVA by lazy {SectionsRVA(requireContext())}
    private lateinit var adapterGoods: GoodsRVA
    private var listSection: List<Section> = listOf()
    private var listGoods: List<Goods> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        unrealInsertion()

        adapterGoods = GoodsRVA(requireContext(), object : ProductClicked{
            override fun itemClicked() {
                requireActivity().findNavController(R.id.fragmentContainer).navigate(R.id.productFragment)
            }
        },false)

        sectionsRV.adapter = adapterSections
        sectionsRV.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL,
            false
        )

        GoodsRV.adapter = adapterGoods
        GoodsRV.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        adapterSections.submitList(listSection)
        adapterGoods.submitList(listGoods)

    }

    private fun unrealInsertion() {

        for (i in 1..10) {
            listSection = if (i == 1)
                listSection + Section(i, "Title $i", null, true)
            else
                listSection + Section(i, "Title $i", null, false)
        }

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