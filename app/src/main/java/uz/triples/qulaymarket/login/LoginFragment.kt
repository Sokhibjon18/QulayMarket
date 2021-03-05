package uz.triples.qulaymarket.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import uz.triples.qulaymarket.gone
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.pojo_objects.LoginWithEmailOrPhoneResponse
import uz.triples.qulaymarket.visible


class LoginFragment : Fragment(R.layout.fragment_login) {

    private val TAG = "LoginFragment"
    private var registration = false;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Cache.loggedIn()) {
            navigateToHomeActivity()
        }

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
                hideKeyboard()
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
                hideKeyboard()
                registration = false
            }
        }

        kirishBtn.setOnClickListener {
            if (registration) {
                val emailOrPhone = phoneNumber.text.toString()

                when {
                    emailOrPhone.isValidMail() -> {
                        findNavController().navigate(R.id.enteringCodeFragment, bundleOf("emailOrPhone" to emailOrPhone, "email" to true))
                    }
                    emailOrPhone.isValidPhoneNumber() -> {
                        findNavController().navigate(R.id.enteringCodeFragment, bundleOf("emailOrPhone" to emailOrPhone, "email" to false))
                    }
                    else -> {
                        Toast.makeText(requireContext(), "Telefon raqam yoki email xato", Toast.LENGTH_SHORT).show()
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
                        val validPhone = if(emailOrPhone.startsWith("+998")) emailOrPhone.substring(4) else emailOrPhone
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
                    Toast.makeText(requireContext(), "Telefon raqam yoki email xato", Toast.LENGTH_SHORT).show()
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

    private fun hideKeyboard(){
        val view = requireActivity().currentFocus
        view?.let {
            val manager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun String.isValidMail():Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isValidPhoneNumber():Boolean{
        val internationalPattern = android.util.Patterns.PHONE.matcher(this).matches()

        val phone = if(this.startsWith("+998")) this.substring(4) else this

        val local = phone.length == 9

        return local && internationalPattern
    }
}