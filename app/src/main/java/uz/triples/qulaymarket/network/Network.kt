package uz.triples.qulaymarket.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.NetworkInterface
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

object Network {

    const val baseUrl = "http://vecume.xyz:8080"

    fun getInstance():Retrofit{
        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }


//    fun getClient(): OkHttpClient.Builder {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val client = OkHttpClient.Builder()
//        client.interceptors().add(interceptor)
//        client.readTimeout(180, TimeUnit.SECONDS)
//        client.connectTimeout(180, TimeUnit.SECONDS)
//
//        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
//        keyStore.load(null, null)
//
//        val sslContext = SSLContext.getInstance("TLS")
//
//        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
//        trustManagerFactory.init(keyStore)
//
//        val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
//        keyManagerFactory.init(keyStore, "keystore_pass".toCharArray())
//
//        sslContext.init(null, trustAllCerts, SecureRandom())
//
//        client.sslSocketFactory(sslContext.socketFactory, trustAllCerts[0]).hostnameVerifier { _, _ -> true }
//
//        return client
//    }
//    private val trustAllCerts = arrayOf(object:X509TrustManager{
//        override fun checkClientTrusted(
//            chain: Array<out X509Certificate>?,
//            authType: String?
//        ) {
//
//        }
//
//        override fun checkServerTrusted(
//            chain: Array<out X509Certificate>?,
//            authType: String?
//        ) {
//
//        }
//
//        override fun getAcceptedIssuers(): Array<X509Certificate> {
//            return arrayOf()
//        }
//
//    })

//    class UnsafeOkHttpClient {
//        companion object {
//            fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
//                try {
//                    // Create a trust manager that does not validate certificate chains
//                    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
//                        @Throws(CertificateException::class)
//                        override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
//                        }
//
//                        @Throws(CertificateException::class)
//                        override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
//                        }
//
//                        override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
//                            return arrayOf()
//                        }
//                    })
//
//                    // Install the all-trusting trust manager
//                    val sslContext = SSLContext.getInstance("SSL")
//                    sslContext.init(null, trustAllCerts, java.security.SecureRandom())
//                    // Create an ssl socket factory with our all-trusting manager
//                    val sslSocketFactory = sslContext.socketFactory
//
//                    val builder = OkHttpClient.Builder()
//                    builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
//                    // builder.hostnameVerifier { _, _ -> true }
//                    builder.hostnameVerifier ( hostnameVerifier = HostnameVerifier{ _, _ -> true })
//
//                    return builder
//                } catch (e: Exception) {
//                    throw RuntimeException(e)
//                }
//            }
//        }
//    }
}