package com.westik.android.convertify.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AllCurrencyResponse(
    val base: String,
    @SerializedName("exchange_rates")
    val exchangeRates: ExchangeRates,
    @SerializedName("last_updated")
    val lastUpdated: Int
) : Serializable