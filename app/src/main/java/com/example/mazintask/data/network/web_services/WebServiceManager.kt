package com.example.mazintask.data.network.web_services

import com.example.mazintask.data.network.utils.RetrofitClientBuilder
import okhttp3.OkHttpClient

object WebServiceManager {

    private val myWebServices: MyWebService
    private val longDelayWebServices: MyWebService
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient()
    }

    init {
        val baseUrl = MyWebService.BASE_URL

        val retrofitClientBuilder = RetrofitClientBuilder.Builder()
            .setBaseUrl(baseUrl)
            .setOkHttpClient(okHttpClient = okHttpClient)
            .setEnableHeadersInterceptor(isEnabled = true)
            .setEnableLoggingInterceptor(isEnabled = true)
            .setEnableRequestInterceptor(isEnabled = false)
            .setEnableResponseInterceptor(isEnabled = false)


        myWebServices = retrofitClientBuilder
            .setReadTime(readTime = 25, writeTime = 25, connectTime = 25)
            .build().generateRetrofitClient().create(MyWebService::class.java)


        longDelayWebServices = retrofitClientBuilder
            .setReadTime(readTime = 45, writeTime = 45, connectTime = 45)
            .build().generateRetrofitClient().create(MyWebService::class.java)
    }

    fun getMyWebServices(longDelayWebService: Boolean = false): MyWebService {
        return if (longDelayWebService) return longDelayWebServices else myWebServices
    }

    fun cancelAllCurrentRequests() {
        okHttpClient.dispatcher().cancelAll()
    }
}