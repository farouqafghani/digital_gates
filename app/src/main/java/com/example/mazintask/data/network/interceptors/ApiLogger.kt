package com.example.mazintask.data.network.interceptors

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.logging.HttpLoggingInterceptor

class ApiLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        var jsonText = message
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                jsonText = GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(JsonParser().parse(message))

            } catch (m: JsonSyntaxException) { }
        }
        println("=======================================")
        println(jsonText)
    }
}
