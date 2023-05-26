package com.westik.android.convertify.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.westik.android.convertify.databinding.FragmentHomeBinding
import com.westik.android.convertify.helpers.ExchangeRatesHelper.Companion.createPairs
import com.westik.android.convertify.helpers.ExchangeRatesHelper.Companion.currencies
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
    private lateinit var searchView: SearchView
    private lateinit var imvNotFound: ImageView
    private lateinit var tvNotFound: TextView

    private lateinit var pairs: List<Pair<String, Double>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        val view = binding.root

        imvNetworkProblem = binding.imvNetworkProblem
        tvNetworkProblem = binding.tvNetworkProblem
        progressBar = binding.loading
        searchView = binding.searchView
        imvNotFound = binding.imvNotFound
        tvNotFound = binding.tvNotFound

        setupRecyclerView()
        setupSearchView()

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
                    pairs = createPairs(it.value.exchangeRates)
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    rvAdapter.updateAdapter(pairs)
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

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (!currencies.contains(query.uppercase())) {
                        notFound()
                    } else {
                        found()
                        val list = listOf(pairs.first{ it.first == query.uppercase()})
                        rvAdapter.updateAdapter(list)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    val list = pairs.filter { it.first.startsWith(newText.uppercase()) }
                    if (list.isNotEmpty()) {
                        rvAdapter.updateAdapter(list)
                    } else {
                        notFound()
                    }
                } else {
                    found()
                    rvAdapter.updateAdapter(pairs)
                }
                return false
            }

        })
        searchView.setOnCloseListener {
            found()
            rvAdapter.updateAdapter(pairs)
            false
        }
    }

    private fun found() {
        recyclerView.visibility = View.VISIBLE
        imvNotFound.visibility = View.GONE
        tvNotFound.visibility = View.GONE
    }

    private fun notFound() {
        recyclerView.visibility = View.GONE
        imvNotFound.visibility = View.VISIBLE
        tvNotFound.visibility = View.VISIBLE
    }

}