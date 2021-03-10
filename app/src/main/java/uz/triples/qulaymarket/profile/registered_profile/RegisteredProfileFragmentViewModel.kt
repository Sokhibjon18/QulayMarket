package uz.triples.qulaymarket.profile.registered_profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.pojo_objects.User
import uz.triples.qulaymarket.utils.Status
import java.lang.Exception

class RegisteredProfileFragmentViewModel(): ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
    get() = _user

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _id = MutableLiveData<String>()
    val id: LiveData<String>
        get() = _id

    init {
        Log.i("TTTT", user.value.toString())
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            _status.value = Status.LOADING
            try {
                val service = Network.getInstance().create(NetWorkInterface::class.java)
                _user.value = service.getUserWithTokenCoroutine(Cache.getToken()).result
                Cache.setUserDetails(_user.value ?: Cache.getUserDetails())
                _status.value = Status.DONE
            } catch (e: Exception){
                _status.value = Status.ERROR
                _user.value = null
                Log.i("TTTT", e.message!!)
            }
        }

//        _status.value = Status.LOADING
//        val service = Network.getInstance().create(NetWorkInterface::class.java)
//        val userResp = service.getUserWithToken(Cache.getToken())
//
//        userResp.enqueue(object : Callback<GetUserResponse>{
//            override fun onResponse(
//                call: Call<GetUserResponse>,
//                response: Response<GetUserResponse>
//            ) {
//                _user.value = response.body()?.result
//                Log.i("TTTT", response.body().toString())
//                _status.value = Status.DONE
//            }
//
//            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
//                _status.value = Status.ERROR
////                _user.value = null
//            }
//
//        })
    }
}