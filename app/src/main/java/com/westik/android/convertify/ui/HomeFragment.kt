package com.westik.android.convertify.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.westik.android.convertify.databinding.FragmentHomeBinding
import com.westik.android.convertify.models.ExchangeRates
import com.westik.android.convertify.models.Resource
import com.westik.android.convertify.viewmodels.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val currencyViewModel: CurrencyViewModel by viewModels()


    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        val view = binding.root

        setupRecyclerView()

        return view
    }

    private fun setupRecyclerView() {
        recyclerView = binding.rvCurrency
        rvAdapter = CurrencyAdapter()

        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        currencyViewModel.allCurrencyResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    rvAdapter.updateAdapter(createPairs(it.value.exchangeRates))
                }
                else -> {
                    Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun createPairs(list: ExchangeRates) = listOf(
            Pair("ARS", list?.ARS),
            Pair("AUD", list?.AUD),
            Pair("BCH", list?.BCH),
            Pair("BGN", list?.BGN),
            Pair("BNB", list?.BNB),
            Pair("BRL", list?.BRL),
            Pair("BTC", list?.BTC),
            Pair("CAD", list?.CAD),
            Pair("CHF", list?.CHF),
            Pair("CNY", list?.CNY),
            Pair("CZK", list?.CZK),
            Pair("DKK", list?.DKK),
            Pair("DOGE", list?.DOGE),
            Pair("DZD", list?.DZD),
            Pair("ETH", list?.ETH),
            Pair("EUR", list?.EUR),
            Pair("GBP", list?.GBP),
            Pair("HKD", list?.HKD),
            Pair("HRK", list?.HRK),
            Pair("HUF", list?.HUF),
            Pair("IDR", list?.IDR),
            Pair("ILS", list?.ILS),
            Pair("INR", list?.INR),
            Pair("ISK", list?.ISK),
            Pair("JPY", list?.JPY),
            Pair("KRW", list?.KRW),
            Pair("LTC", list?.LTC),
            Pair("MAD", list?.MAD),
            Pair("MXN", list?.MXN),
            Pair("MYR", list?.MYR),
            Pair("NOK", list?.NOK),
            Pair("NZD", list?.NZD),
            Pair("PHP", list?.PHP),
            Pair("PLN", list?.PLN),
            Pair("RON", list?.RON),
            Pair("SEK", list?.SEK),
            Pair("SGD", list?.SGD),
            Pair("THB", list?.THB),
            Pair("TRY", list?.TRY),
            Pair("TWD", list?.TWD),
            Pair("USD", list?.USD),
            Pair("XRP", list?.XRP),
            Pair("ZAR", list?.ZAR)
        )

}