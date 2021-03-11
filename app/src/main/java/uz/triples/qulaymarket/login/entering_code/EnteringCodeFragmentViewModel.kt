package uz.triples.qulaymarket.login.entering_code

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.fragment_entering_code.*
import kotlinx.coroutines.launch
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.database.Cache
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.utils.Status

class EnteringCodeFragmentViewModel: ViewModel() {

    private var code = ""
    private lateinit var timerC: CountDownTimer

    private val _activateStatus = MutableLiveData<Status>()
    val activateStatus: LiveData<Status>
        get() = _activateStatus

    private val _isTimerGoing = MutableLiveData<Boolean>()
    val isTimerGoing:LiveData<Boolean>
        get() = _isTimerGoing

    private val _codeInserted = MutableLiveData<Boolean>()
    val codeInserted:LiveData<Boolean>
        get() = _codeInserted

    private val _timer = MutableLiveData<String>()
    val timer:LiveData<String>
        get() = _timer

    private val _num1 = MutableLiveData<String>()
    val num1:LiveData<String>
        get() = _num1

    private val _num2 = MutableLiveData<String>()
    val num2:LiveData<String>
        get() = _num2

    private val _num3 = MutableLiveData<String>()
    val num3:LiveData<String>
        get() = _num3

    private val _num4 = MutableLiveData<String>()
    val num4:LiveData<String>
        get() = _num4

    init {
        startTimer()
    }

    fun numClicked(view: View) {
        if(isTimerGoing.value == true){
            val isFull = code.length == 4
            when (view.id) {
                R.id.one -> if (code.length<4) code += "1"
                R.id.two -> if (code.length<4) code += "2"
                R.id.three -> if (code.length<4) code += "3"
                R.id.four -> if (code.length<4) code += "4"
                R.id.five -> if (code.length<4) code += "5"
                R.id.six -> if (code.length<4) code += "6"
                R.id.seven -> if (code.length<4) code += "7"
                R.id.eight -> if (code.length<4) code += "8"
                R.id.nine -> if (code.length<4) code += "9"
                R.id.zero -> if (code.length<4) code += "0"
                R.id.erase -> {
                    if (code.isNotEmpty())
                        code = code.substring(0, code.length - 1)
                }
            }

            val length = code.length
            _num1.value = if(length > 0) code[0].toString() else ""
            _num2.value = if (length > 1) code[1].toString() else ""
            _num3.value = if (length > 2) code[2].toString() else ""
            _num4.value = if (length > 3) code[3].toString() else ""

            if(length == 4 && !isFull){
                _codeInserted.value = true
            }
        }
    }

    fun startTimer(){
        _isTimerGoing.value = true
        timerC = object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                _timer.value = "00:${timerConverter((p0 / 1000).toInt())}"
            }

            override fun onFinish() {
                endTimer()
            }

            fun timerConverter(time: Int): String {
                return if (time < 10)
                    "0$time"
                else
                    time.toString()
            }

        }.start()
    }

    fun onResendClick(view: View){
        startTimer()
    }

    fun endTimer(){
        _isTimerGoing.value = false
        _timer.value = "00:00"
        code=""
        _num1.value = ""
        _num2.value = ""
        _num3.value = ""
        _num4.value = ""
        timerC.cancel()
    }

    fun activateEmail(email: String){
        _activateStatus.value = Status.LOADING
        viewModelScope.launch {
            try {
                val service = Network.getInstance().create(NetWorkInterface::class.java)
                val resp = service.activateEmail(email, code, Cache.getToken())
                Log.i("YYYY", resp.toString() + email + " $code  ${Cache.getToken()}")
                _activateStatus.value = if(resp.ok == true && resp.result != null) Status.DONE else Status.ERROR
            } catch (e:Exception){
                _activateStatus.value = Status.ERROR
            }
        }
    }

    fun activatePhone(phone: String) {
        _activateStatus.value = Status.LOADING
        viewModelScope.launch {
            try {
                val service = Network.getInstance().create(NetWorkInterface::class.java)
                val resp = service.activateEmail(phone, code, Cache.getToken())
                _activateStatus.value = if(resp.ok == true && resp.result != null) Status.DONE else Status.ERROR
            } catch (e:Exception){
                _activateStatus.value = Status.ERROR
            }
        }
    }
}