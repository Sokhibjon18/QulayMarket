package uz.triples.qulaymarket.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import uz.triples.qulaymarket.HomeActivity
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.gone
import uz.triples.qulaymarket.visible

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val TAG = "LoginFragment"
    private var registration = false;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                kirish.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
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
                kirish.setTextColor(ContextCompat.getColor(requireContext(), R.color.darkBlue))
                royxatdanOtish.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
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
                requireActivity().finish()
                startActivity(
                    Intent(
                        requireActivity(),
                        HomeActivity::class.java
                    )
                )
            }
        }

        forgetPassword.setOnClickListener {
            royxatdanOtish.callOnClick()
        }
    }
}