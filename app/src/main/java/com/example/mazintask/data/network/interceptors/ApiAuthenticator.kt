package com.example.mazintask.data.network.interceptors

import com.example.mazintask.data.manager.LoggedInUserManager
import com.example.mazintask.data.manager.LoggedInUserManager.user
import com.example.mazintask.data.models.User
import com.example.mazintask.data.network.web_services.WebServiceManager
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class ApiAuthenticator : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        val refreshTokenResponse =
            WebServiceManager.getMyWebServices()
                .refreshToken(refresh_token = user?.tokenResponse?.refreshToken ?: "").execute()

        if (refreshTokenResponse.isSuccessful) {
            val newAccessToken = refreshTokenResponse.body()?.accessToken ?: ""

            // Update current user
            val user = User(refreshTokenResponse.body())
            LoggedInUserManager.user = user

            // continue on the original request with the new token
            val originalRequest = response.request()
            val urlBuilder = originalRequest.url().newBuilder()
            urlBuilder.setPathSegment(1, newAccessToken)

            return originalRequest.newBuilder()
                .url(urlBuilder.build())
                .build()
        }
        return null
    }
}