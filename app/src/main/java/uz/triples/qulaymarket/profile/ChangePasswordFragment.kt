package uz.triples.qulaymarket.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_change_password.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.pojo_objects.GetUserResponse

class ChangePasswordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_change_password,
            container,
            false
        )

        view.back_button.setOnClickListener {
            findNavController().navigate(R.id.action_changePasswordFragment_to_myProfileDetails)
        }

        view.save_button.setOnClickListener {
            val password = view.old_password.editText?.text.toString()
            val newPassword = view.new_password.editText?.text.toString()
            val confirmPassword = view.new_passpord_again.editText?.text.toString()

            if(newPassword != confirmPassword){
                new_passpord_again.editText?.error = "Yuqoridagi parol bilan to'g'ri kelmadi"
            } else if(password != Cache.getUserPassword()){
                old_password.editText?.error = "Parol noto'g'ri kiritildi"
            } else{
                val service = Network.getInstance().create(NetWorkInterface::class.java)
                val changePassword = service.updateUserPassword(password, newPassword, Cache.getToken())

                changePassword.enqueue(object: Callback<GetUserResponse> {
                    override fun onResponse(
                        call: Call<GetUserResponse>,
                        response: Response<GetUserResponse>
                    ) {
                        findNavController().navigate(R.id.action_changeNameFragment_to_myProfileDetails)
                        if(response.body()?.ok == true){
                            Toast.makeText(requireContext(), "Parol o'zgartirildi", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }
        }

        return view
    }
}