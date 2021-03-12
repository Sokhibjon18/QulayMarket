package uz.triples.qulaymarket.login.login_or_register.login_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.databinding.FragmentLoginPageBinding
import uz.triples.qulaymarket.utils.*

class LoginPage private constructor() : Fragment() {

    private var block : (()->Unit)? = null
    private lateinit var binding: FragmentLoginPageBinding
    private val viewModel: LoginPageViewModel by lazy {
        ViewModelProviders.of(this).get(LoginPageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().hideKeyboard()

        binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.toastText.observe(viewLifecycleOwner){
            this.showToast(it)
        }

        viewModel.loginStatus.observe(viewLifecycleOwner){
            if(it){
                this.navigateToHomeActivity()
            }
        }

        binding.forgetPassword.setOnClickListener {
            block?.invoke()
        }

        binding.enterBtn.setOnClickListener {
            val emailOrPhone = binding.phoneNumber.text.toString()
            val password = binding.password.editText?.text.toString()

            if(System.currentTimeMillis() - Cache.getLastClickTime() < 1000){
                return@setOnClickListener
            }
            if(emailOrPhone.isBlank() || password.isBlank()){
                this.showToast("Login va parol kiriting")
                return@setOnClickListener
            }
            if(!emailOrPhone.isValidMail() && !emailOrPhone.isValidPhoneNumber()){
                binding.phoneNumber.error = "To'g'ri formatda kiriting"
                return@setOnClickListener
            }

            if(isConnectedNetwork(requireContext())){
                viewModel.onEnterClicked(emailOrPhone, password)
            } else {
                this.showToast("Internet ulanmagan")
            }
        }

        return binding.root
    }

    fun setOnForgetPasswordClickListener(block : ()->Unit){
        this.block = block
    }

    companion object{
        private var page: LoginPage? = null
        fun getInstance():LoginPage{
            if(page == null){
                page = LoginPage()
            }

            return page!!
        }
    }
}