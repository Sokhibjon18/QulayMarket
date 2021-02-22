package uz.triples.qulaymarket.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recently_searched.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.triples.qulaymarket.models.RecentlyUsed
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.`interface`.DeleteSearchedItem
import uz.triples.qulaymarket.database.AppDatabase
import uz.triples.qulaymarket.database.SearchDao
import uz.triples.qulaymarket.home.adapters.RecentlyUsedRVA

class RecentlySearchedFragment : Fragment(R.layout.fragment_recently_searched) {

    private lateinit var adapter: RecentlyUsedRVA
    private val dao: SearchDao by lazy {
        AppDatabase.getInstance(requireContext())!!.searchDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecentlyUsedRVA(
            requireContext(),
            object : DeleteSearchedItem {
                override fun deleteItem(text: String) {
                    CoroutineScope(Dispatchers.IO).launch {
                        dao.deleteSearch(RecentlyUsed(text))
                    }
                }
            })

        recentlyUsedRV.adapter = adapter
        recentlyUsedRV.layoutManager = LinearLayoutManager(requireContext())

        dao.getAllWords().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

}