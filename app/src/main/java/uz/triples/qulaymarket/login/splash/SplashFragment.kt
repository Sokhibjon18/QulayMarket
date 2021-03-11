package uz.triples.qulaymarket.login.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private val viewModel: SplashFragmentViewModel by lazy{
        ViewModelProviders.of(this).get(SplashFragmentViewModel::class.java)
    }
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSplashBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        viewModel.startCounting()

        viewModel.finishSplash.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment,
                null, NavOptions.Builder().setPopUpTo(R.id.splashFragment,
                        true).setEnterAnim(android.R.anim.fade_in).build())
                viewModel.splashFinished()
            }
        }

        return binding.root
    }
}