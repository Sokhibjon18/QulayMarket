package uz.triples.qulaymarket.login.login_or_register.register_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.utils.isValidMail
import java.lang.Exception

class RegisterPageViewModel: ViewModel() {
    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String>
        get() = _toastText

    private val _registerStatus = MutableLiveData<Boolean>()
    val registerStatus: LiveData<Boolean>
        get() = _registerStatus

    fun onEnterClicked(emailOrPhone: String) {
        val service = Network.getInstance().create(NetWorkInterface::class.java)

        viewModelScope.launch {
            try {
                val logInResponse = if (emailOrPhone.isValidMail()) {
                    service.registerWithEmail(emailOrPhone, Cache.getTemporaryPasswordForRegister())
                } else {
                    val validPhone =
                        if (emailOrPhone.startsWith("+998")) emailOrPhone.substring(
                            4
                        ) else emailOrPhone
                    service.registerWithPhone(validPhone, Cache.getTemporaryPasswordForRegister())
                }

                if (logInResponse.ok == true) {
                    _registerStatus.value = true
                    Cache.setToken(logInResponse.result ?: "NoToken")

                } else {
                    _toastText.value = "Login yoki parol xato."
                    _registerStatus.value = false
                }
            } catch (e: Exception) {
                _registerStatus.value = false
            }
        }
    }
}