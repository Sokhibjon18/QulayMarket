package uz.triples.qulaymarket.profile

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.pojo_objects.GetUserResponse
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import uz.triples.qulaymarket.database.*
import uz.triples.qulaymarket.network.pojo_objects.User
import uz.triples.qulaymarket.utils.gone
import uz.triples.qulaymarket.utils.visible
import java.lang.Math.abs

class ChangeBirthdayFragment : Fragment() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_change_birthday, container,false)

        loadPickerButtons(view)

        view.birthday.editText?.setOnTouchListener { v, event ->
            val drawableRight = 2

            if(event.action == MotionEvent.ACTION_UP) {
                if(event.rawX >= ((view.birthday.editText as EditText).right - (view.birthday.editText as EditText).compoundDrawables[drawableRight].bounds.width())) {
                    if(view.birthday_picker_container.visibility == View.VISIBLE){
                        view.birthday_picker_container.gone()
                    } else{
                        view.birthday_picker_container.visible()
                    }
                }
            }
            false
        }

        view.birthday.editText?.setText(requireArguments().getCharSequence("birthdate"))

        view.save_button.setOnClickListener {
            val text = view.birthday.editText?.text.toString()
            if(text.isBlank() || text.isNotValidBirthday()){
                Toast.makeText(requireContext(), "Tug'ilgan kuningizni kiriting", Toast.LENGTH_SHORT).show()
            } else{
                insertBirthday(text)
            }
        }

        view.back_button.setOnClickListener {
            findNavController().navigate(R.id.action_changeBirthdayFragment_to_myProfileDetails)
        }

        return view
    }

    private fun loadPickerButtons(v: View) {
        v.confirm_picker.setOnClickListener {
            val year = v.birthday_picker.year
            val month = if(v.birthday_picker.month < 10) "0${v.birthday_picker.month+1}" else "${v.birthday_picker.month}"
            val day = if(v.birthday_picker.dayOfMonth < 10) "0${v.birthday_picker.dayOfMonth}" else "${v.birthday_picker.dayOfMonth}"
            val date = "$day-$month-$year"

            if(v.birthday_picker.year < Calendar.getInstance().get(Calendar.YEAR) - 1){
                v.birthday.editText?.setText(date)
                v.birthday_picker_container.gone()
            }
        }
        v.cancel_picker.setOnClickListener {
            v.birthday_picker_container.gone()
        }
    }

    private fun String.isNotValidBirthday(): Boolean{
        if(this.length != 10 || this[2] != '-' || this[5] != '-'
            || this.substring(0,1).toIntOrNull() == null
            || this.substring(3, 4).toIntOrNull() == null
            || this.substring(6, 9).toIntOrNull() ==null
        ) {
            Toast.makeText(requireContext(), "Tug'ilgan kuningizni to'g'ri formatda kiriting", Toast.LENGTH_SHORT).show()
            return true
        }

        return false
    }

    private fun insertBirthday(text: String) {
        val service = Network.getInstance().create(NetWorkInterface::class.java)
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = dateFormat.parse(text)

        Log.i("MMMM", "${(date.time/1000).toInt()}")

        val user = Cache.getUserDetails()

        val changeBirthDate = service.updateUserBirthDate("${(date.time/1000).toInt()}", Cache.getToken())

        changeBirthDate.enqueue(object: Callback<GetUserResponse> {
            override fun onResponse(
                call: Call<GetUserResponse>,
                response: Response<GetUserResponse>
            ) {
                Cache.setUserDetails(response.body()?.result ?: Cache.getUserDetails())
                findNavController().navigate(R.id.action_changeBirthdayFragment_to_myProfileDetails)
            }

            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}