package uz.triples.qulaymarket.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_my_profile_details.view.*
import uz.triples.qulaymarket.R

class MyProfileDetails : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_my_profile_details, container, false)

        view.name_container.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changeNameFragment)
        }

        view.birthday_container.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changeBirthdayFragment)
        }

        view.email_container.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changeEmailFragment)
        }

        view.phone_number_container.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changePhoneNumberFragment)
        }

        view.password_container.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileDetails_to_changePasswordFragment)
        }

        return view
    }
}