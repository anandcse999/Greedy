package com.example.baselibrary.api

import okhttp3.Interceptor
import okhttp3.Response

class TagsInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.url().newBuilder()
            .addQueryParameter("api_key", "8908bab8739a63a222fc6c191deb9e9c")
            .addQueryParameter("format", "json")


        val url = builder.build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}