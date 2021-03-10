package uz.triples.qulaymarket.profile.my_details

import android.R.string
import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.pojo_objects.GetUserResponse
import uz.triples.qulaymarket.network.pojo_objects.User
import uz.triples.qulaymarket.utils.Status
import java.io.ByteArrayOutputStream


class MyDetailsViewModel: ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private val _uploadStatus = MutableLiveData<Status>()
    val uploadStatus: LiveData<Status>
        get() = _uploadStatus

    init {
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
            }
        }
    }

    fun uploadImage(bitmap: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val encodedImage: String = Base64.encodeToString(
            byteArrayOutputStream.toByteArray(),
            Base64.DEFAULT
        )

        Log.i("TTTT", encodedImage)

        val service = Network.getInstance().create(NetWorkInterface::class.java)
        val uploadImage = service.uploadImage(encodedImage, Cache.getToken())

        _uploadStatus.value = Status.LOADING
        uploadImage.enqueue(object : Callback<GetUserResponse> {
            override fun onResponse(
                call: Call<GetUserResponse>,
                response: Response<GetUserResponse>
            ) {
                _uploadStatus.value = Status.DONE
            }

            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                _uploadStatus.value = Status.ERROR
                Log.i("TTTT", t.message ?: "ccccc")
            }

        })

//        viewModelScope.launch {
//            _uploadStatus.value = Status.LOADING
//            try {
//                val service = Network.getInstance().create(NetWorkInterface::class.java)
//                service.uploadImage(encodedImage, Cache.getToken())
//                _uploadStatus.value = Status.DONE
//                Log.i("TTTT", "uploaded $encodedImage")
//            } catch (e: java.lang.Exception){
//                Log.i("TTTT", e.message ?: "ccccc")
//                _uploadStatus.value = Status.ERROR
//            }
//        }
    }
}