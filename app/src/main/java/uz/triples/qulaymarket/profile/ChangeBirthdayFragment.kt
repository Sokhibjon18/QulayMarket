package uz.triples.qulaymarket.profile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_change_birthday.view.*
import uz.triples.qulaymarket.R

class ChangeBirthdayFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_change_birthday, container,false)

        view.birthday.editText?.setOnTouchListener { v, event ->
            val drawableRight = 2

            if(event.action == MotionEvent.ACTION_UP) {
                if(event.rawX >= ((view.birthday.editText as EditText).right - (view.birthday.editText as EditText).compoundDrawables[drawableRight].bounds.width())) {
                    Toast.makeText(requireContext(), "Calendar icon click!", Toast.LENGTH_SHORT).show()
                    
                }
            }
            true
        }

        return view
    }
}