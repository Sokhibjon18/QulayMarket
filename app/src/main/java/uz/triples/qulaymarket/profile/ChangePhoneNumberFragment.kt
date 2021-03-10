package uz.triples.qulaymarket.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_change_phone_number.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.pojo_objects.GetUserResponse

class ChangePhoneNumberFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_change_phone_number, container, false)

        view.save_button.setOnClickListener {
            val text = view.phone_number.editText?.text.toString()
            if(text.isBlank()){
                Toast.makeText(requireContext(), "Telefon raqamingizni kiriting", Toast.LENGTH_SHORT).show()
            } else{
                updatePhone(text)
            }
        }

        view.back_button.setOnClickListener {
            findNavController().navigate(R.id.action_changePhoneNumberFragment_to_myProfileDetails2)
        }

        return view
    }

    private fun updatePhone(text: String) {
        if(!text.isValidPhoneNumber()){
            Toast.makeText(requireContext(), "Telefon raqamingizni to'g'ri kiriting", Toast.LENGTH_SHORT).show()
            return
        }

        val service = Network.getInstance().create(NetWorkInterface::class.java)
        val updateName = service.updateUserPhone(text, Cache.getToken())

        updateName.enqueue(object: Callback<GetUserResponse> {
            override fun onResponse(
                call: Call<GetUserResponse>,
                response: Response<GetUserResponse>
            ) {
                Cache.setUserDetails(response.body()?.result ?: Cache.getUserDetails())
                findNavController().navigate(R.id.action_changePhoneNumberFragment_to_myProfileDetails2)
            }

            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun String.isValidPhoneNumber():Boolean{
        val internationalPattern = android.util.Patterns.PHONE.matcher(this).matches()

        val phone = if(this.startsWith("+998")) this.substring(4) else this

        val local = phone.length == 9

        return local && internationalPattern
    }
}