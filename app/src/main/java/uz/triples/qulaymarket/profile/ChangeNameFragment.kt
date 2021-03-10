package uz.triples.qulaymarket.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_change_email.view.*
import kotlinx.android.synthetic.main.fragment_change_name.view.*
import kotlinx.android.synthetic.main.fragment_change_name.view.back_button
import kotlinx.android.synthetic.main.fragment_change_name.view.save_button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.pojo_objects.GetUserResponse

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
                updateName(text)
            }
        }

        view.back_button.setOnClickListener {
            findNavController().navigate(R.id.action_changeNameFragment_to_myProfileDetails)
        }

        return view
    }

    private fun updateName(text: String) {
        val service = Network.getInstance().create(NetWorkInterface::class.java)
        val updateName = service.updateUserName(text, Cache.getToken())

        updateName.enqueue(object: Callback<GetUserResponse>{
            override fun onResponse(
                call: Call<GetUserResponse>,
                response: Response<GetUserResponse>
            ) {
                Cache.setUserDetails(response.body()?.result ?: Cache.getUserDetails())
                findNavController().navigate(R.id.action_changeNameFragment_to_myProfileDetails)
            }

            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}