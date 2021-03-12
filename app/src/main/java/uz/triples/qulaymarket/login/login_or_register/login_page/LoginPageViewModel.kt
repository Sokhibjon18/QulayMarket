package uz.triples.qulaymarket.login.login_or_register.login_page

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

class LoginPageViewModel : ViewModel() {

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String>
        get() = _toastText

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean>
        get() = _loginStatus

    fun onEnterClicked(emailOrPhone: String, password: String) {
        val service = Network.getInstance().create(NetWorkInterface::class.java)

        viewModelScope.launch {
            try {
                val logInResponse = if (emailOrPhone.isValidMail()) {
                    service.logInWithEmail(emailOrPhone, password)
                } else {
                    val validPhone =
                        if (emailOrPhone.startsWith("+998")) emailOrPhone.substring(
                            4
                        ) else emailOrPhone
                    service.logInWithPhone(validPhone, password)
                }

                if (logInResponse.ok == true) {
                    _loginStatus.value = true
                    Cache.setLoggedInStatus(true)
                    Cache.setUserPassword(password)
                    Cache.setToken(logInResponse.result ?: "NoToken")
                } else {
                    _toastText.value = "Login yoki parol xato."
                    _loginStatus.value = false
                }
            } catch (e: Exception) {
                _loginStatus.value = false
            }
        }
    }
}