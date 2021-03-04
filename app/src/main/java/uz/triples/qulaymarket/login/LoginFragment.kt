package uz.triples.qulaymarket.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.triples.qulaymarket.HomeActivity
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.gone
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.Network.baseUrl
import uz.triples.qulaymarket.network.pojo_objects.LoginWithEmailResponse
import uz.triples.qulaymarket.visible


class LoginFragment : Fragment(R.layout.fragment_login) {

    private val TAG = "LoginFragment"
    private var registration = false;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(Cache.loggedIn()){
            navigateToHomeActivity()
        }

        kirish.callOnClick()

        acceptRequirements.gone()

        royxatdanOtish.setOnClickListener {
            if (!registration) {
                indicator.animate().translationX(indicator.translationX + indicator.width)
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
                password.gone()
                forgetPassword.gone()
                acceptRequirements.visible()
                registration = true
            }
        }

        kirish.setOnClickListener {
            if (registration) {
                indicator.animate().translationX(indicator.translationX - indicator.width)
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
                registration = false
            }
        }

        kirishBtn.setOnClickListener {
            if (registration)
                findNavController().navigate(R.id.enteringCodeFragment)
            else {
                val email = phoneNumber.text.toString()
                val password = password.editText?.text.toString()

                val service = Network.getInstance().create(NetWorkInterface::class.java)
                val logInResponse = service.logInWithEmail(email, password)

                logInResponse.enqueue(object: Callback<LoginWithEmailResponse> {
                    override fun onResponse(
                        call: Call<LoginWithEmailResponse>,
                        response: Response<LoginWithEmailResponse>
                    ) {
                        if(response.isSuccessful){
                            val res = response.body()
                            if(res?.ok == true){
                                navigateToHomeActivity()
                            } else{
                                Toast.makeText(requireContext(), "Login yoki parol xato.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(
                        call: Call<LoginWithEmailResponse>,
                        t: Throwable
                    ) {
                        Toast.makeText(requireContext(), "Muammo yuz berdi ${t.toString()}", Toast.LENGTH_SHORT).show()
                    }

                })


                //navigateToHomeActivity()
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