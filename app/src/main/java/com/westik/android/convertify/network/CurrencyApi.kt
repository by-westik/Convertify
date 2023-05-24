package com.westik.android.convertify.network

import com.westik.android.convertify.helpers.Constants
import com.westik.android.convertify.models.AllCurrencyResponse
import retrofit2.http.GET

interface CurrencyApi {

    @GET(Constants.MAIN_URL)
    suspend fun getAllCurrency() : AllCurrencyResponse

}