package com.example.mazintask.data.models.response

import com.example.mazintask.data.models.parts.ProductNameList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductNamesResponse {
    @SerializedName("ProductNameList")
    @Expose
    var productNameList: List<ProductNameList>? = null

}