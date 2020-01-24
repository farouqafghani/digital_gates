package com.example.mazintask.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ResponseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return chain.proceed(originalRequest)
    }
}