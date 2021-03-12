package uz.triples.qulaymarket.login.login_or_register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.databinding.FragmentLoginBinding
import uz.triples.qulaymarket.login.login_or_register.login_page.LoginPage

class LoginOrRegisterFragment : Fragment(R.layout.fragment_login) {

    private val login = LoginPage.getInstance()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)

        binding.tabLayout.setupWithViewPager(binding.viewPager)

        login.setOnForgetPasswordClickListener {
            Log.i("UUUU", "selected")
            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(1))
        }

        return binding.root
    }


//        kirishBtn.setOnClickListener {
//            if (registration) {
//                val emailOrPhone = phoneNumber.text.toString()
//                val service = Network.getInstance().create(NetWorkInterface::class.java)
//
//                when {
//                    emailOrPhone.isValidMail() -> {
//                        val registerWithEmail = service.registerWithEmail(
//                            emailOrPhone,
//                            Cache.getTemporaryPasswordForRegister()
//                        )
//                        registerWithEmail.enqueue(object :
//                            Callback<LoginWithEmailOrPhoneResponse> {
//                            override fun onResponse(
//                                call: Call<LoginWithEmailOrPhoneResponse>,
//                                response: Response<LoginWithEmailOrPhoneResponse>
//                            ) {
//                                //if(false){
//                                Cache.setToken(
//                                    response.body()?.result ?: "NoToken"
//                                )
//                                findNavController().navigate(
//                                    R.id.action_loginFragment_to_enteringCodeFragment,
//                                    bundleOf(
//                                        "emailOrPhone" to emailOrPhone,
//                                        "email" to true
//                                    )
//                                )
//                                //}
//
//                                Log.i("AAAA", response.body().toString())
//                            }
//
//                            override fun onFailure(
//                                call: Call<LoginWithEmailOrPhoneResponse>,
//                                t: Throwable
//                            ) {
//
//                            }
//
//                        })
//
//                    }
//                    emailOrPhone.isValidPhoneNumber() -> {
//                        findNavController().navigate(
//                            R.id.enteringCodeFragment, bundleOf(
//                                "emailOrPhone" to emailOrPhone,
//                                "email" to false
//                            )
//                        )
//                    }
//                    else -> {
//                        Toast.makeText(
//                            requireContext(),
//                            "Telefon raqam yoki email xato",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            }
}
