package com.westik.android.convertify.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.westik.android.convertify.R
import com.westik.android.convertify.databinding.FragmentConverterBinding
import com.westik.android.convertify.models.Currency

class ConverterFragment : Fragment() {

    private var _binding: FragmentConverterBinding? = null
    private val binding: FragmentConverterBinding
        get() = _binding!!

    lateinit var tvCurrency: TextView
    lateinit var etRubAmount: TextInputEditText
    lateinit var etCurrencyAmount: TextInputEditText

    private val args: ConverterFragmentArgs by navArgs()
    var rubTextWatcher: TextWatcher? = null
    var curTextWatcher: TextWatcher? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        val view = binding.root

        tvCurrency = binding.tvCurrency
        etRubAmount = binding.etRubAmount
        etCurrencyAmount = binding.etCurrencyAmount

        setupStartValue(args.currencyArgument)

        val cur = args.currencyArgument

        setupStartValue(cur)

        rubTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                etCurrencyAmount.removeTextChangedListener(curTextWatcher)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    val currency = s.toString().toDouble() * cur.value
                    etCurrencyAmount.setText(currency.toString())
                    tvCurrency.text = getString(R.string.currency_convert_title, String.format("%.3f", s.toString().toDouble()), String.format("%.3f", currency), cur.title)
                } else {
                    tvCurrency.text = getString(R.string.currency_convert_title, "1,000", String.format("%.3f", cur.value), cur.title)
                    etCurrencyAmount.setText("")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                etCurrencyAmount.addTextChangedListener(curTextWatcher)
            }

        }

        curTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                etRubAmount.removeTextChangedListener(rubTextWatcher)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    val rub = s.toString().toDouble() / cur.value
                    etRubAmount.setText(rub.toString())
                    tvCurrency.text =
                        getString(R.string.currency_convert_title, String.format("%.3f", rub), String.format("%.3f", s.toString().toDouble()), cur.title)
                } else {
                    tvCurrency.text = getString(R.string.currency_convert_title, "1,000", String.format("%.3f", cur.value), cur.title)
                    etRubAmount.setText("")
                }
            }

            override fun afterTextChanged(s: Editable?) {
                etRubAmount.addTextChangedListener(rubTextWatcher)
            }

        }

        etRubAmount.addTextChangedListener(rubTextWatcher)
        etCurrencyAmount.addTextChangedListener(curTextWatcher)


        return view
    }

    private fun setupStartValue(currency: Currency) {
        tvCurrency.text = getString(R.string.currency_convert_title, "1,000", String.format("%.3f", currency.value), currency.title)
        etRubAmount.setText("1")
        etCurrencyAmount.setText(currency.value.toString())
    }

}