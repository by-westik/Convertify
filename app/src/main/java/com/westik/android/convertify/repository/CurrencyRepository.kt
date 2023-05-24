package com.westik.android.convertify.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.westik.android.convertify.network.CurrencyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val api: CurrencyApi
) : BaseRepository() {

    suspend fun getAllCurrency() = safeApiCall {
        api.getAllCurrency()
    }

}