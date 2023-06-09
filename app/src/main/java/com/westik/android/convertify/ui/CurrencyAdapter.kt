package com.westik.android.convertify.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.westik.android.convertify.databinding.CurrencyItemBinding

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
    var pairs: List<Pair<String, Double>>? = null,
    private val onItemClick: (item: Pair<String, Double>) -> Unit
) : RecyclerView.Adapter<CurrencyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(CurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    // TODO как-то это исправить
    override fun getItemCount(): Int = pairs?.size ?: 0

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        if (pairs != null) {
            val item = pairs!![position]
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }



    fun updateAdapter(list: List<Pair<String, Double>>) {
        this.pairs = list
        notifyDataSetChanged()
    }
}