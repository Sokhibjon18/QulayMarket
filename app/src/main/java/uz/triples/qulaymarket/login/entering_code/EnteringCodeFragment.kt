package uz.triples.qulaymarket.login.entering_code

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_entering_code.*
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.databinding.FragmentEnteringCodeBinding
import uz.triples.qulaymarket.utils.Status

class EnteringCodeFragment : Fragment(R.layout.fragment_entering_code) {

    private var isEmail: Boolean? = null
    private var emailOrPhone: String? = null

    private val viewModel: EnteringCodeFragmentViewModel by lazy{
        ViewModelProviders.of(this).get(EnteringCodeFragmentViewModel::class.java)
    }
    private lateinit var binding: FragmentEnteringCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        isEmail = requireArguments().getBoolean("email")
        emailOrPhone = requireArguments().getString("emailOrPhone")

        binding = FragmentEnteringCodeBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.isTimerGoing.observe(viewLifecycleOwner){
            if(it){
                binding.resend.setTextColor(resources.getColor(R.color.gray))
                binding.resend.isClickable = false
            } else{
                binding.resend.setTextColor(resources.getColor(R.color.lightBlue))
                binding.resend.isClickable = true
            }
        }

        viewModel.codeInserted.observe(viewLifecycleOwner){
            if(it){
                if(isEmail == true){
                    viewModel.activateEmail(emailOrPhone!!)
                } else {
                    viewModel.activatePhone(emailOrPhone!!)
                }
            }
        }

        viewModel.activateStatus.observe(viewLifecycleOwner){
            when(it){
                Status.DONE ->
                    findNavController().navigate(R.id.action_enteringCodeFragment_to_settingPasswordFragment)

                Status.ERROR ->
                    Toast.makeText(requireContext(), "Kod xato", Toast.LENGTH_SHORT).show()
                else -> Log.i("TTTT", "Loading")
            }
        }

        return binding.root
    }

    override fun onPause() {
        viewModel.endTimer()
        super.onPause()
    }
}