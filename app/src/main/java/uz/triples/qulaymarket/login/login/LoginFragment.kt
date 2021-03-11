package uz.triples.qulaymarket.login.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.triples.qulaymarket.HomeActivity
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.pojo_objects.LoginWithEmailOrPhoneResponse
import uz.triples.qulaymarket.utils.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val TAG = "LoginFragment"
    private var registration = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kirish.callOnClick()

        acceptRequirements.gone()

        royxatdanOtish.setOnClickListener {
            if (!registration) {
                indicator.animate()
                    .translationX(indicator.translationX + indicator.width)
                    .setDuration(200).start()
                royxatdanOtish.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkBlue
                    )
                )
                kirish.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.grey
                    )
                )
                requireActivity().hideKeyboard()
                password.gone()
                forgetPassword.gone()
                acceptRequirements.visible()
                phoneNumber.setText("")
                registration = true
            }
        }

        kirish.setOnClickListener {
            if (registration) {
                indicator.animate()
                    .translationX(indicator.translationX - indicator.width)
                    .setDuration(200).start()
                kirish.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.darkBlue
                    )
                )
                royxatdanOtish.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.grey
                    )
                )
                password.visible()
                acceptRequirements.gone()
                forgetPassword.visible()
                requireActivity().hideKeyboard()
                registration = false
            }
        }

        kirishBtn.setOnClickListener {
            if (registration) {
                val emailOrPhone = phoneNumber.text.toString()
                val service = Network.getInstance().create(NetWorkInterface::class.java)

                when {
                    emailOrPhone.isValidMail() -> {
                        val registerWithEmail = service.registerWithEmail(
                            emailOrPhone,
                            Cache.getTemporaryPasswordForRegister()
                        )
                        registerWithEmail.enqueue(object :
                            Callback<LoginWithEmailOrPhoneResponse> {
                            override fun onResponse(
                                call: Call<LoginWithEmailOrPhoneResponse>,
                                response: Response<LoginWithEmailOrPhoneResponse>
                            ) {
                                //if(false){
                                Cache.setToken(
                                    response.body()?.result ?: "NoToken"
                                )
                                findNavController().navigate(
                                    R.id.action_loginFragment_to_enteringCodeFragment,
                                    bundleOf(
                                        "emailOrPhone" to emailOrPhone,
                                        "email" to true
                                    )
                                )
                                //}

                                Log.i("AAAA", response.body().toString())
                            }

                            override fun onFailure(
                                call: Call<LoginWithEmailOrPhoneResponse>,
                                t: Throwable
                            ) {

                            }

                        })

                    }
                    emailOrPhone.isValidPhoneNumber() -> {
                        findNavController().navigate(
                            R.id.enteringCodeFragment, bundleOf(
                                "emailOrPhone" to emailOrPhone,
                                "email" to false
                            )
                        )
                    }
                    else -> {
                        Toast.makeText(
                            requireContext(),
                            "Telefon raqam yoki email xato",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                val emailOrPhone = phoneNumber.text.toString()
                val password = password.editText?.text.toString()
                val service = Network.getInstance().create(NetWorkInterface::class.java)

                if(emailOrPhone.isValidPhoneNumber() || emailOrPhone.isValidMail()){
                    val logInResponse = if(emailOrPhone.isValidMail()){
                        service.logInWithEmail(emailOrPhone, password)
                    } else{
                        val validPhone = if(emailOrPhone.startsWith("+998")) emailOrPhone.substring(
                            4
                        ) else emailOrPhone
                        service.logInWithPhone(validPhone, password)
                    }

                    logInResponse.enqueue(object :
                        Callback<LoginWithEmailOrPhoneResponse> {
                        override fun onResponse(
                            call: Call<LoginWithEmailOrPhoneResponse>,
                            orPhoneResponse: Response<LoginWithEmailOrPhoneResponse>
                        ) {
                            if (orPhoneResponse.isSuccessful) {
                                val res = orPhoneResponse.body()
                                if (res?.ok == true) {
                                    navigateToHomeActivity()
                                    Cache.setLoggedInStatus(true)
                                    Cache.setUserPassword(password)
                                    Cache.setToken(res.result ?: "NoToken")
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Login yoki parol xato.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }

                        override fun onFailure(
                            call: Call<LoginWithEmailOrPhoneResponse>,
                            t: Throwable
                        ) {
                            Toast.makeText(
                                requireContext(),
                                "Muammo yuz berdi ${t.toString()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })
                } else{
                    Toast.makeText(
                        requireContext(),
                        "Telefon raqam yoki email xato",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        forgetPassword.setOnClickListener {
            royxatdanOtish.callOnClick()
        }
    }

    private fun navigateToHomeActivity() {
        requireActivity().finish()
        startActivity(
            Intent(
                requireActivity(),
                HomeActivity::class.java
            )
        )
    }
}
