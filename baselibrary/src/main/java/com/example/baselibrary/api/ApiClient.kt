package com.example.baselibrary.api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.baselibrary.api.ApiConstants.BASE_URL
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


object ApiClient {

    private var retrofit: Retrofit? = null
    private var httpClient: OkHttpClient? = null

    private fun getRequestHeader(): OkHttpClient? {
        if (httpClient == null) {
            httpClient = OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(TagsInterceptor())
//                .addInterceptor { chain ->
//                    val request = chain.request()
//                    val response = chain.proceed(request)
//                    Log.v(
//                        "RETROFIT",
//                        response.code().toString() + " RESPONSE CODE " + response.code()
//                    )
//                    response
//                }
                .build()
        }
        return httpClient
    }

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRequestHeader())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}