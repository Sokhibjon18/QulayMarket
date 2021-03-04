package uz.triples.qulaymarket.database

import android.content.Context
import android.content.SharedPreferences

object Cache {
    private lateinit var context: Context
    private lateinit var preferences: SharedPreferences

    fun initialize(c: Context){
        context = c

        preferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE)
    }

    fun loggedIn():Boolean{
        return preferences.getBoolean("loggedIn", false)
    }

    fun setLoggedInStatus(status: Boolean){
        preferences.edit().putBoolean("loggedIn", status).apply()
    }
}