package com.westik.android.convertify.ui

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.westik.android.convertify.databinding.CurrencyItemBinding
import com.westik.android.convertify.models.ExchangeRates
import java.lang.Exception

class CurrencyViewHolder(
    private val binding: CurrencyItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Pair<String, Double?>) {
        binding.apply {
            tvCurrencyTitle.text = item.first
            tvCurrencyRate.text = String.format("%.5f", item.second)
        }

    }

}
class CurrencyAdapter(
    var pairs: List<Pair<String, Double?>>? = null
) : RecyclerView.Adapter<CurrencyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(CurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    // TODO как-то это исправить
    override fun getItemCount(): Int = 43

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        if (pairs != null) {
            val item = pairs!![position]
            Log.d(TAG, "ITEM = ${item.first} ${item.second}")
            holder.bind(item)
        }
    }



    fun updateAdapter(list: List<Pair<String, Double?>>) {
        this.pairs = list
        notifyDataSetChanged()
    }
}