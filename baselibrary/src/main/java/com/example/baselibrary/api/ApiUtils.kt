package com.example.baselibrary.api

object ApiUtils {
    private fun ApiUtils() {}

    fun getAPIService(): RestApiInterface? {
        return ApiClient.getClient()!!.create(RestApiInterface::class.java)
    }

}