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
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_change_birthday.view.*
import kotlinx.android.synthetic.main.fragment_change_birthday.view.save_button
import uz.triples.qulaymarket.R
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

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
                    if(view.birthday_picker.visibility == View.VISIBLE){
                        view.birthday_picker.visibility = View.GONE
                    } else{
                        view.birthday_picker.visibility = View.VISIBLE
                        val date = try {
                            SimpleDateFormat("dd-MM-yyyy", Locale.ROOT).parse((view.birthday.editText as EditText).text.toString())
                        } catch (e: Exception){
                            Date()
                        }
                        view.birthday_picker.clearSelection()
                        view.birthday_picker.setDateSelected(date,true)
                        view.birthday_picker.setCurrentDate(date)
                    }
                }
            }
            false
        }

        view.birthday_picker.setOnDateChangedListener { widget, date, selected ->
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)

            val dateText = dateFormat.format(date.date)

            view.birthday.editText?.setText(dateText)
            view.birthday_picker.visibility = View.GONE
        }

        view.save_button.setOnClickListener {
            val text = view.birthday.editText?.text.toString()
            if(text.isBlank()){
                Toast.makeText(requireContext(), "Tug'ilgan kuningizni kiriting", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(requireContext(), "Birthday is inserted to the database", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_changeBirthdayFragment_to_myProfileDetails)
            }
        }

        view.back_button.setOnClickListener {
            findNavController().navigate(R.id.action_changeBirthdayFragment_to_myProfileDetails)
        }

        return view
    }
}