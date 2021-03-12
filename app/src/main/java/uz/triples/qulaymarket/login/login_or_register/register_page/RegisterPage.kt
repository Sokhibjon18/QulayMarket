package uz.triples.qulaymarket.login.login_or_register.register_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.databinding.FragmentRegisterPageBinding
import uz.triples.qulaymarket.utils.*

class RegisterPage : Fragment() {

    private lateinit var binding: FragmentRegisterPageBinding
    private val viewModel by lazy{
        ViewModelProviders.of(this).get(RegisterPageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().hideKeyboard(binding.phoneNumber)

        binding = FragmentRegisterPageBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val emailOrPhone = binding.phoneNumber.text.toString()

        viewModel.toastText.observe(viewLifecycleOwner){
            this.showToast(it)
        }

        viewModel.registerStatus.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(R.id.action_loginFragment_to_enteringCodeFragment,
                bundleOf("emailOrPhone" to emailOrPhone, "email" to emailOrPhone.isValidMail()))
            }
        }

        binding.enterBtn.setOnClickListener {
            if(System.currentTimeMillis() - Cache.getLastClickTime() < 1000){
                return@setOnClickListener
            }
            if(emailOrPhone.isBlank()){
                this.showToast("Login va parol kiriting")
                return@setOnClickListener
            }
            if(!emailOrPhone.isValidMail() && !emailOrPhone.isValidPhoneNumber()){
                binding.phoneNumber.error = "To'g'ri formatda kiriting"
                return@setOnClickListener
            }

            if(isConnectedNetwork(requireContext())){
                viewModel.onEnterClicked(emailOrPhone)
            } else {
                this.showToast("Internet ulanmagan")
            }
        }

        binding.phoneNumber.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                requireActivity().hideKeyboard()
            }
        }

        return binding.root
    }
}