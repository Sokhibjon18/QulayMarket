package uz.triples.qulaymarket.database

import android.content.Context
import android.content.SharedPreferences
import uz.triples.qulaymarket.network.pojo_objects.User

object Cache {
    private lateinit var preferences: SharedPreferences

    fun initialize(preferences: SharedPreferences){
        this.preferences = preferences

    }

    fun loggedIn():Boolean{
        return preferences.getBoolean("loggedIn", false)
    }

    fun setLoggedInStatus(status: Boolean){
        preferences.edit().putBoolean("loggedIn", status).apply()
    }

    fun setUserDetails(user: User){
        preferences.edit().putString("userName", user.name).apply()
        preferences.edit().putString("userEmail", user.email).apply()
        preferences.edit().putString("userPhone", user.phone).apply()
        preferences.edit().putString("userBirthDate", user.birthdate).apply()
        preferences.edit().putInt("userId", user.id!!).apply()
    }

    fun getUserDetails():User{
        val name = preferences.getString("userName", "null")
        val email = preferences.getString("userEmail", "null")
        val phone = preferences.getString("userPhone", "null")
        val birthDate = preferences.getString("userBirthDate", "null")
        val id = preferences.getInt("userId", -1)

        return User(birthDate, phone, name, id, email)
    }

    fun setToken(token: String){
        preferences.edit().putString("token", token).apply()
    }

    fun getToken():String{
        return preferences.getString("token", "NoToken")!!
    }

    fun getUserPassword(): String {

        return preferences.getString("userPassword", "null")!!
    }

    fun setUserPassword(password: String){
        preferences.edit().putString("userPassword", password).apply()
    }
}