package com.example.mazintask.data.network.web_services

import com.example.mazintask.data.models.response.ProductNamesResponse
import com.example.mazintask.data.models.response.SearchResponse
import com.example.mazintask.data.models.response.TokenResponse
import retrofit2.Call
import retrofit2.http.*

interface MyWebService {

    companion object {
        const val BASE_URL = "https://test-apis.speedyservices.com/"
    }

    @FormUrlEncoded
    @POST("/oauth2/token")
    fun authorizeUser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("client_id") clientId: String,
        @Field("grant_type") grantType: String
    ): Call<TokenResponse>

    @FormUrlEncoded
    @POST("/oauth/token")
    fun refreshToken(
        @Field("refresh_token") refresh_token: String,
        @Field("grant_type") grant_type: String? = "refresh_token",
        @Field("client_id") clientId: String? = "mobile"
    ): Call<TokenResponse?>

    @GET("api/Product/Names")
    fun getSearchAutoCompleteProducts(): Call<ProductNamesResponse>

    @GET("/api/Products/Search")
    fun searchByKey(
        @Query("SearchTerm") SearchTerm: String,
        @Query("CatalogueType") CatalogueType: String? = "HireAndBuy",
        @Query("GetPrices") GetPrices: String? = "All",
        @Query("PageSize") pageSize: Int? = 25
    ): Call<SearchResponse>
}