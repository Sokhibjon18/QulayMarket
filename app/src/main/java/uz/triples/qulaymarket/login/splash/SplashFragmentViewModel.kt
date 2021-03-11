package uz.triples.qulaymarket.login.splash

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import uz.triples.qulaymarket.R

class SplashFragmentViewModel: ViewModel() {

    private val _finishSplash = MutableLiveData<Boolean>()
    val finishSplash: LiveData<Boolean>
        get() = _finishSplash

    init {
        splashFinished()
    }

    fun splashFinished() {
        _finishSplash.value = false
    }

    fun startCounting() {
        object : CountDownTimer(2500,2500){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                _finishSplash.value = true
                this.cancel()
            }

        }.start()
    }


}