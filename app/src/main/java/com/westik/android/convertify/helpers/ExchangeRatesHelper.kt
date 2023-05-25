package com.westik.android.convertify.helpers

import com.westik.android.convertify.models.ExchangeRates

class ExchangeRatesHelper {

    companion object {
        fun createPairs(list: ExchangeRates) = listOf(
            Pair("ARS", list.ARS),
            Pair("AUD", list.AUD),
            Pair("BCH", list.BCH),
            Pair("BGN", list.BGN),
            Pair("BNB", list.BNB),
            Pair("BRL", list.BRL),
            Pair("BTC", list.BTC),
            Pair("CAD", list.CAD),
            Pair("CHF", list.CHF),
            Pair("CNY", list.CNY),
            Pair("CZK", list.CZK),
            Pair("DKK", list.DKK),
            Pair("DOGE", list.DOGE),
            Pair("DZD", list.DZD),
            Pair("ETH", list.ETH),
            Pair("EUR", list.EUR),
            Pair("GBP", list.GBP),
            Pair("HKD", list.HKD),
            Pair("HRK", list.HRK),
            Pair("HUF", list.HUF),
            Pair("IDR", list.IDR),
            Pair("ILS", list.ILS),
            Pair("INR", list.INR),
            Pair("ISK", list.ISK),
            Pair("JPY", list.JPY),
            Pair("KRW", list.KRW),
            Pair("LTC", list.LTC),
            Pair("MAD", list.MAD),
            Pair("MXN", list.MXN),
            Pair("MYR", list.MYR),
            Pair("NOK", list.NOK),
            Pair("NZD", list.NZD),
            Pair("PHP", list.PHP),
            Pair("PLN", list.PLN),
            Pair("RON", list.RON),
            Pair("SEK", list.SEK),
            Pair("SGD", list.SGD),
            Pair("THB", list.THB),
            Pair("TRY", list.TRY),
            Pair("TWD", list.TWD),
            Pair("USD", list.USD),
            Pair("XRP", list.XRP),
            Pair("ZAR", list.ZAR)
        )
    }
}