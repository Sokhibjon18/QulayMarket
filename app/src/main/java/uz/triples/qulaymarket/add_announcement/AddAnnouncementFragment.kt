package uz.triples.qulaymarket.add_announcement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_announcement.view.*
import uz.triples.qulaymarket.R

class AddAnnouncementFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_announcement, container, false)

        view.cancel_button.setOnClickListener {
            findNavController().navigate(R.id.action_addAnnouncementFragment_to_mainFragment)
        }

        return view
    }
}