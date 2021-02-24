package uz.triples.qulaymarket.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_change_name.view.*
import uz.triples.qulaymarket.R

class ChangeNameFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_change_name, container, false)

        view.save_button.setOnClickListener {
            val text = view.name.editText?.text.toString()
            if(text.isBlank()){
                Toast.makeText(requireContext(), "Ismingizni kiriting", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(requireContext(), "Name is inserted to the database", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_changeNameFragment_to_myProfileDetails)
            }
        }

        return view
    }
}