package com.example.mazintask.data.network.utils

import com.example.mazintask.data.network.interceptors.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Farouq Afghani on 24/11/2019.
 */
class RetrofitClientBuilder private constructor(
    val baseUrl: String = "",
    val okHttpClient: OkHttpClient? = null,
    val enableLoggingInterceptor: Boolean = true,
    val enableHeadersInterceptor: Boolean = false,
    val enableRequestInterceptor: Boolean = false,
    val enableResponseInterceptor: Boolean = false,
    val otherInterceptors: MutableList<Interceptor> = ArrayList(),
    val readTime: Long,
    val writeTime: Long,
    val connectTime: Long
) {

    var okHttpClientBuilder: OkHttpClient.Builder =
        okHttpClient?.newBuilder() ?: UnsafeOkHttpClient().getUnsafeOkHttpClient().newBuilder()

    init {
        // Logging Interceptor
        if (enableLoggingInterceptor) {
            val httpLoggingInterceptor = HttpLoggingInterceptor(ApiLogger())
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
        }

        // Headers Interceptor
        if (enableHeadersInterceptor) {
            okHttpClientBuilder.addInterceptor(HeadersInterceptor())
        }

        // Add other interceptors if exists
        for (interceptors in otherInterceptors) {
            okHttpClientBuilder.addInterceptor(interceptors)
        }

        // Response Interceptor
        if (enableResponseInterceptor) {
            okHttpClientBuilder.addInterceptor(ResponseInterceptor())
        }

        // Request Interceptor
        if (enableRequestInterceptor) {
            okHttpClientBuilder.addInterceptor(RequestInterceptor())
        }

        // Authenticator Interceptor
        okHttpClientBuilder.authenticator(ApiAuthenticator())
        //endregion

        // Time
        okHttpClientBuilder.readTimeout(readTime, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(writeTime, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(connectTime, TimeUnit.SECONDS)
    }

    fun generateRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }

    data class Builder(
        private var baseUrl: String = "",
        private var okHttpClient: OkHttpClient? = null,
        private var enableHeadersInterceptor: Boolean = true,
        private var enableLoggingInterceptor: Boolean = true,
        private var enableRequestInterceptor: Boolean = true,
        private var enableResponseInterceptor: Boolean = true,
        private var otherInterceptors: MutableList<Interceptor> = ArrayList(),
        private var readTime: Long = 15,
        private var writeTime: Long = 15,
        private var connectTime: Long = 15
    ) {

        fun setBaseUrl(baseUrl: String) =
            apply { this.baseUrl = baseUrl }

        fun setOkHttpClient(okHttpClient: OkHttpClient) =
            apply { this.okHttpClient = okHttpClient }

        fun setNewOkHttpClient(safeClient: Boolean) =
            apply {
                this.okHttpClient =
                    if (safeClient) OkHttpClient() else UnsafeOkHttpClient().getUnsafeOkHttpClient()
            }

        fun setEnableHeadersInterceptor(isEnabled: Boolean) =
            apply { this.enableHeadersInterceptor = isEnabled }

        fun setEnableLoggingInterceptor(isEnabled: Boolean) =
            apply { this.enableLoggingInterceptor = isEnabled }

        fun setEnableRequestInterceptor(isEnabled: Boolean) =
            apply { this.enableRequestInterceptor = isEnabled }

        fun setEnableResponseInterceptor(isEnabled: Boolean) =
            apply { this.enableResponseInterceptor = isEnabled }

        fun addInterceptor(interceptor: Interceptor) =
            apply { this.otherInterceptors.add(interceptor) }

        fun setReadTime(readTime: Long, writeTime: Long, connectTime: Long) =
            apply {
                this.readTime = readTime
                this.writeTime = writeTime
                this.connectTime = connectTime
            }

        fun build() =
            RetrofitClientBuilder(
                baseUrl = baseUrl,
                okHttpClient = okHttpClient,
                enableHeadersInterceptor = enableHeadersInterceptor,
                enableLoggingInterceptor = enableLoggingInterceptor,
                enableRequestInterceptor = enableRequestInterceptor,
                enableResponseInterceptor = enableResponseInterceptor,
                otherInterceptors = otherInterceptors,
                readTime = readTime,
                writeTime = writeTime,
                connectTime = connectTime
            )
    }
}