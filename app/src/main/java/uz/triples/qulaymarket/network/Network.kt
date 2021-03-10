package uz.triples.qulaymarket.network

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.triples.qulaymarket.database.Cache

object Network {

    const val baseUrl = "http://vecume.xyz:8080"

    fun getInstance():Retrofit{
        val gson = GsonBuilder().setLenient().create()

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(object:Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val token = Cache.getToken()
                Log.i("TTTT", token)
                val request = chain.request().newBuilder().addHeader("Token", token).build()
                return chain.proceed(request)
            }

        })

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))

            .build()

        return retrofit
    }
}