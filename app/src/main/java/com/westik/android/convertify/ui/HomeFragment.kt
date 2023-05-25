package com.westik.android.convertify.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.westik.android.convertify.databinding.FragmentHomeBinding
import com.westik.android.convertify.helpers.ExchangeRatesHelper.Companion.createPairs
import com.westik.android.convertify.models.Currency
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
    private lateinit var imvNetworkProblem: ImageView
    private lateinit var tvNetworkProblem: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        val view = binding.root

        imvNetworkProblem = binding.imvNetworkProblem
        tvNetworkProblem = binding.tvNetworkProblem
        progressBar = binding.loading

        setupRecyclerView()

        return view
    }

    private fun click(item: Currency) {
        val action = HomeFragmentDirections.actionHomeFragmentToConverterFragment(item)
        findNavController().navigate(action)
    }

    private fun setupRecyclerView() {
        recyclerView = binding.rvCurrency
        val currencyClick = {item: Pair<String, Double> -> click(Currency(item.first, item.second))}
        rvAdapter = CurrencyAdapter(null, currencyClick)

        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        currencyViewModel.allCurrencyResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    rvAdapter.updateAdapter(createPairs(it.value.exchangeRates))
                }
                else -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    imvNetworkProblem.visibility = View.VISIBLE
                    tvNetworkProblem.visibility = View.VISIBLE
                }
            }
        }

    }


}