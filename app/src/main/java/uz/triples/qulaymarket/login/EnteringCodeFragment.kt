package uz.triples.qulaymarket.login

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_entering_code.*
import uz.triples.qulaymarket.R

class EnteringCodeFragment : Fragment(R.layout.fragment_entering_code) {

    private var code = ""
    private lateinit var timerC: CountDownTimer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        one.setOnClickListener { numClicked(R.id.one) }
        two.setOnClickListener { numClicked(R.id.two) }
        three.setOnClickListener { numClicked(R.id.three) }
        fore.setOnClickListener { numClicked(R.id.fore) }
        five.setOnClickListener { numClicked(R.id.five) }
        six.setOnClickListener { numClicked(R.id.six) }
        seven.setOnClickListener { numClicked(R.id.seven) }
        eight.setOnClickListener { numClicked(R.id.eight) }
        nine.setOnClickListener { numClicked(R.id.nine) }
        zero.setOnClickListener { numClicked(R.id.zero) }
        erase.setOnClickListener { numClicked(R.id.erase) }

        timerC = object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                timer.text = "00:${timerConvertor((p0 / 1000).toInt())}"
            }

            override fun onFinish() {
                resend.setTextColor(resources.getColor(R.color.darkBlue))
                resend.setOnClickListener { timerC.start() }
            }

            fun timerConvertor(time: Int): String {
                return if (time < 10)
                    "0$time"
                else
                    time.toString()
            }

        }.start()

        resend.setTextColor(resources.getColor(R.color.grey))
    }

    private fun numClicked(id: Int) {
        when (id) {
            R.id.one -> if (code.length<4) code += "1"
            R.id.two -> if (code.length<4) code += "2"
            R.id.three -> if (code.length<4) code += "3"
            R.id.fore -> if (code.length<4) code += "4"
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
        if (length > 0) num1.text = code[0].toString() else num1.text = ""
        if (length > 1) num2.text = code[1].toString() else num2.text = ""
        if (length > 2) num3.text = code[2].toString() else num3.text = ""
        if (length > 3) num4.text = code[3].toString() else num4.text = ""
    }


    override fun onPause() {
        timerC.cancel()
        super.onPause()
    }
}