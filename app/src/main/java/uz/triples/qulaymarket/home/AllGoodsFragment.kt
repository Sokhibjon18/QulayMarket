package uz.triples.qulaymarket.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import uz.triples.qulaymarket.models.Section
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.`interface`.ProductClicked
import uz.triples.qulaymarket.databinding.FragmentAllGoodsBinding
import uz.triples.qulaymarket.home.adapters.GoodsRVA
import uz.triples.qulaymarket.home.adapters.SectionsRVA
import uz.triples.qulaymarket.network.pojo_objects.Announcement
import uz.triples.qulaymarket.utils.Status

class AllGoodsFragment : Fragment() {

    private val adapterSections: SectionsRVA by lazy {SectionsRVA(requireContext())}
    private lateinit var adapterGoods: GoodsRVA
    private var listSection = arrayListOf<Section>()

    private lateinit var binding: FragmentAllGoodsBinding
    private val viewModel: AllGoodsFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(AllGoodsFragmentViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAllGoodsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.status.observe(viewLifecycleOwner){
            if(it == Status.LOADING){
                binding.refreshSpinner.visibility = View.VISIBLE
                binding.refreshSpinner.start()
            } else if(it == Status.DONE){
                binding.refreshSpinner.visibility = View.GONE
                binding.refreshSpinner.stop()
            }
        }

        adapterGoods = GoodsRVA(requireContext(), object : ProductClicked{
            override fun itemClicked(announcement: Announcement) {
                requireActivity().findNavController(R.id.fragmentContainer).navigate(R.id.action_mainFragment_to_productFragment, bundleOf("announcement" to announcement))
            }
        },false)

        binding.GoodsRV.adapter = adapterGoods

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sectionsRV.adapter = adapterSections

        adapterSections.submitList(listSection)
    }
}