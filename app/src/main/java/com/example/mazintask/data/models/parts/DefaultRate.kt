package com.example.mazintask.data.models.parts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DefaultRate {
    @SerializedName("RateId")
    @Expose
    var rateId: String? = null
    @SerializedName("Rate")
    @Expose
    var rate: Double? = null
    @SerializedName("CurrencySymbol")
    @Expose
    var currencySymbol: String? = null
    @SerializedName("RateText")
    @Expose
    var rateText: String? = null
    @SerializedName("ExtraDayRate")
    @Expose
    var extraDayRate: Double? = null
    @SerializedName("ExtraDayRateText")
    @Expose
    var extraDayRateText: String? = null

}