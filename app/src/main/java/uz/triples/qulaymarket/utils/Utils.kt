package uz.triples.qulaymarket.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.toast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun showLog(st: String, tag: String = "MyTag") =
    Log.d(tag, st)

fun Fragment.showToast(text: String){
    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

@SuppressLint("ServiceCast")
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

enum class Status {LOADING, ERROR, DONE}

fun String.isValidMail():Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(
    this
).matches()
fun String.isValidPhoneNumber():Boolean{
    val internationalPattern = android.util.Patterns.PHONE.matcher(this).matches()

    val phone = when {
        this.startsWith("+998") -> this.substring(4)
        this.startsWith("998") -> this.substring(3)
        else -> this
    }

    val local = if(this.startsWith("+998")) phone.length == 9 else phone.length == 8

    return local && internationalPattern
}