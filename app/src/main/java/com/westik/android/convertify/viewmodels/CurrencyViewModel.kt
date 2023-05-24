package com.westik.android.convertify.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.westik.android.convertify.models.AllCurrencyResponse
import com.westik.android.convertify.models.Resource
import com.westik.android.convertify.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val _allCurrencyResponse: MutableLiveData<Resource<AllCurrencyResponse>> = MutableLiveData()
    val allCurrencyResponse: LiveData<Resource<AllCurrencyResponse>>
        get() = _allCurrencyResponse

    init {
        viewModelScope.launch {
            _allCurrencyResponse.value = currencyRepository.getAllCurrency()
        }
    }

}