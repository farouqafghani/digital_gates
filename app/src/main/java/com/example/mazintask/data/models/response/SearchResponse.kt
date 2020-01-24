package com.example.mazintask.data.models.response

import com.example.mazintask.data.models.parts.Product
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResponse {
    @SerializedName("SearchTerm")
    @Expose
    var searchTerm: String? = null
    @SerializedName("Hierarchy")
    @Expose
    var hierarchy: Any? = null
    @SerializedName("Products")
    @Expose
    var products: List<Product>? = null
//    @SerializedName("_priceEnquiryServiceCallResult")
//    @Expose
//    var priceEnquiryServiceCallResult: PriceEnquiryServiceCallResult? = null
//    @SerializedName("Filters")
//    @Expose
//    var filters: Filters? = null
//    @SerializedName("SortOrder")
//    @Expose
//    var sortOrder: SortOrder? = null
//    @SerializedName("Success")
//    @Expose
//    var success: Boolean? = null
//    @SerializedName("Message")
//    @Expose
//    var message: Any? = null
//    @SerializedName("Summary")
//    @Expose
//    var summary: Summary? = null

}