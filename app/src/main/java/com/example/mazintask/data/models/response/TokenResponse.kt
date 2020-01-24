package com.example.mazintask.data.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TokenResponse {

    @SerializedName("access_token")
    @Expose
    var accessToken: String? = null
    @SerializedName("token_type")
    @Expose
    var tokenType: String? = null
    @SerializedName("expires_in")
    @Expose
    var expiresIn: Int? = null
    @SerializedName("refresh_token")
    @Expose
    var refreshToken: String? = null
    @SerializedName("as:client_id")
    @Expose
    var asClientId: String? = null
    @SerializedName("userName")
    @Expose
    var userName: String? = null
    @SerializedName(".issued")
    @Expose
    var issued: String? = null
    @SerializedName(".expires")
    @Expose
    var expires: String? = null

}