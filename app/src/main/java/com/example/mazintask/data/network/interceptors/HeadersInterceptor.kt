package com.example.mazintask.data.network.interceptors

import com.example.mazintask.data.manager.LoggedInUserManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeadersInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val tokenResponse = LoggedInUserManager.user?.tokenResponse
        val token = "${tokenResponse?.tokenType} ${tokenResponse?.accessToken}"

        request = request.newBuilder()
            .addHeader("Authorization", token)
            .build()
        return chain.proceed(request)
    }
}