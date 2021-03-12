package uz.triples.qulaymarket.network

import retrofit2.Call
import retrofit2.http.*
import uz.triples.qulaymarket.network.pojo_objects.*

interface NetWorkInterface {

    @GET("/login")
    suspend fun logInWithEmail(@Query("email") email: String, @Query("password") password: String): LoginWithEmailOrPhoneResponse

    @GET("/login")
    suspend fun logInWithPhone(@Query("phone") email: String, @Query("password") password: String): LoginWithEmailOrPhoneResponse

    @GET("/register")
    suspend fun registerWithEmail(@Query("email") email: String, @Query("password") password: String): LoginWithEmailOrPhoneResponse

    @GET("/register")
    suspend fun registerWithPhone(@Query("phone") email: String, @Query("password") password: String): LoginWithEmailOrPhoneResponse

    @GET("/activate")
    suspend fun activateEmail(@Query("email") email: String, @Query("code") password: String, @Header("Token") token: String): ActivateResult

    @GET("/activate")
    suspend fun activatePhone(@Query("phone") email: String, @Query("code") password: String, @Header("Token") token: String): ActivateResult


    @GET("/announcements")
    suspend fun getAllAnnouncements(): GetAllAnnouncements

    @GET("/tags")
    suspend fun getTags(): GetTagsResponse

    @GET("/announcements/search")
    suspend fun searchFromAnnouncements(@Query("value") value: String): GetAllAnnouncements

    @GET("/user")
    suspend fun getUserWithTokenCoroutine(@Header("Token") token: String): GetUserResponse

    @FormUrlEncoded
    @PUT("/user")
    fun uploadImage(@Field("image") image: String, @Header("Token") token:String):Call<GetUserResponse>

    @PUT("/user")
    fun updateUserBirthDate(@Query("birthdate") birthdate:String, @Header("Token") token:String):Call<GetUserResponse>

    @PUT("/user")
    fun updateUserName(@Query("name") name:String, @Header("Token") token:String):Call<GetUserResponse>

    @PUT("/user")
    fun updateUserEmail(@Query("email") email:String, @Header("Token") token:String):Call<GetUserResponse>

    @PUT("/user")
    fun updateUserPhone(@Query("phone") phone:String, @Header("Token") token:String):Call<GetUserResponse>

    @PUT("/user")
    fun updateUserPassword(@Query("password") password:String,
                           @Query("new_password") newPassword:String,
                           @Header("Token") token:String):Call<GetUserResponse>

}