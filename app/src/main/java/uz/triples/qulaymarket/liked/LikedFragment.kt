package uz.triples.qulaymarket.liked

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_liked.view.*
import uz.triples.qulaymarket.R

class LikedFragment : Fragment(R.layout.fragment_liked) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.backLiked.setOnClickListener {
            findNavController().navigate(R.id.action_likedFragment_to_mainFragment)
        }
    }
}