package com.example.mazintask.data.models.parts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductNameList {
    @SerializedName("Name")
    @Expose
    var name: String? = null
    @SerializedName("ProductCode")
    @Expose
    var productCode: String? = null
    @SerializedName("CategoryName")
    @Expose
    var categoryName: String? = null
    @SerializedName("CategoryFormattedName")
    @Expose
    var categoryFormattedName: String? = null
    @SerializedName("_links")
    @Expose
    var links: List<Link>? = null

}