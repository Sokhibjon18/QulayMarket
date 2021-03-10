package uz.triples.qulaymarket.profile.registered_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.databinding.FragmentProfileRegisteredBinding
import uz.triples.qulaymarket.utils.Status
import uz.triples.qulaymarket.utils.gone
import uz.triples.qulaymarket.utils.visible

class RegisteredProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileRegisteredBinding
    private val viewModel: RegisteredProfileFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(RegisteredProfileFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileRegisteredBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.status.observe(viewLifecycleOwner){
            when (it) {
                Status.LOADING -> {
                    binding.refreshSpinner.apply {
                        visible()
                        start()
                    }
                }
                Status.DONE -> {
                    binding.refreshSpinner.apply {
                        gone()
                        stop()
                    }
                }
                else -> {
                    binding.refreshSpinner.apply {
                        gone()
                        stop()
                        Toast.makeText(requireContext(), "Xatolik yuz berdi", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.myProfileContainer.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_myProfileDetails)
        }


        return binding.root
    }
}