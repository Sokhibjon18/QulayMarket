package uz.triples.qulaymarket.liked

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.fragment_liked.view.*
import uz.triples.qulaymarket.R

class LikedFragment : Fragment(R.layout.fragment_liked) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.backLiked.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}