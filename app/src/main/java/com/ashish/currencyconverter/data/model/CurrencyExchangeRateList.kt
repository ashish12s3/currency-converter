package com.ashish.currencyconverter.data.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class CurrencyExchangeRateList (
    @SerializedName("success")
    val isSuccess: Boolean,
    @SerializedName("terms")
    val terms:String,
    @SerializedName("privacy")
    val privacy:String,
    @SerializedName("timestamp")
    val timestamp:Long,
    @SerializedName("quotes")
    val quotes:JsonElement,
    @SerializedName("source")
    val source:String

)