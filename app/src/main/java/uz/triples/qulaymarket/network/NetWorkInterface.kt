package uz.triples.qulaymarket.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.triples.qulaymarket.network.pojo_objects.LoginWithEmailOrPhoneResponse
import uz.triples.qulaymarket.network.pojo_objects.RegisterWithEmailResponse

interface NetWorkInterface {

    @GET("/register?email={email}&password={password}")
    fun registerUserWithEmail(@Path("email") email: String, @Path("password") password: String): Call<RegisterWithEmailResponse>

    @GET("/login")
    fun logInWithEmail(@Query("email") email: String, @Query("password") password: String): Call<LoginWithEmailOrPhoneResponse>

    @GET("/login")
    fun logInWithPhone(@Query("phone") email: String, @Query("password") password: String): Call<LoginWithEmailOrPhoneResponse>

}