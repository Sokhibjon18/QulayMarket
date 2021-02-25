package uz.triples.qulaymarket.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_change_email.view.*
import uz.triples.qulaymarket.R

class ChangeEmailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_change_email, container, false)

        view.save_button.setOnClickListener {
            val text = view.email.editText?.text.toString()
            if(text.isBlank()){
                Toast.makeText(requireContext(), "Emailni kiriting", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(requireContext(), "Email is inserted to the database", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_changeEmailFragment_to_myProfileDetails)
            }
        }

        view.back_button.setOnClickListener {
            findNavController().navigate(R.id.action_changeEmailFragment_to_myProfileDetails)
        }

        return view
    }
}