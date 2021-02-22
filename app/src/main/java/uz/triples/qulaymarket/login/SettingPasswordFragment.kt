package uz.triples.qulaymarket.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_setting_password.*
import uz.triples.qulaymarket.HomeActivity
import uz.triples.qulaymarket.R

class SettingPasswordFragment : Fragment(R.layout.fragment_setting_password) {

    private val TAG = "SettingPasswordFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kirishBtnSettingPassword.setOnClickListener {
            if (passwordYield1.text!!.isNotBlank()) {
                if (passwordYield1.text!!.length < 6) {
                    passwordYield1.error = "Eng kamida 6 ta belgi kiriting"
                    return@setOnClickListener
                }
                if (passwordYield1.text.toString() != passwordYield2.text.toString()){
                    passwordYield2.error = "Parolni xato kiritdingiz"
                    return@setOnClickListener
                }

                startActivity(Intent(requireActivity(), HomeActivity::class.java))
            }
        }
    }
}