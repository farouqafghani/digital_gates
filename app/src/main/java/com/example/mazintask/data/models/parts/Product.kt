package com.example.mazintask.data.models.parts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Product {
    @SerializedName("_links")
    @Expose
    var links: List<Link>? = null
    @SerializedName("ProductCode")
    @Expose
    var productCode: String? = null
    @SerializedName("Name")
    @Expose
    var name: String? = null
    @SerializedName("ImageUrl")
    @Expose
    var imageUrl: String? = null
    @SerializedName("ResizedImageUrl")
    @Expose
    var resizedImageUrl: String? = null
    @SerializedName("DefaultRateFullText")
    @Expose
    var defaultRateFullText: String? = null
    @SerializedName("DefaultRate")
    @Expose
    var defaultRate: DefaultRate? = null
    @SerializedName("DiscountedFromRateFullText")
    @Expose
    var discountedFromRateFullText: Any? = null
    @SerializedName("DiscountedFromRate")
    @Expose
    var discountedFromRate: Any? = null
    @SerializedName("HasProductDetails")
    @Expose
    var hasProductDetails: Boolean? = null
    @SerializedName("HasPackageItems")
    @Expose
    var hasPackageItems: Boolean? = null
    @SerializedName("Type")
    @Expose
    var type: String? = null
    @SerializedName("TopProduct")
    @Expose
    var topProduct: Boolean? = null
    @SerializedName("CapitalCommitment")
    @Expose
    var capitalCommitment: Boolean? = null
    @SerializedName("IsRestricted")
    @Expose
    var isRestricted: Boolean? = null
    @SerializedName("Messages")
    @Expose
    var messages: Any? = null
    @SerializedName("Restrictions")
    @Expose
    var restrictions: Any? = null
    @SerializedName("OnlineOnly")
    @Expose
    var onlineOnly: Boolean? = null

}