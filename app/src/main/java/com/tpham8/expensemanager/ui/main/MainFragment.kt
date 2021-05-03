package com.tpham8.expensemanager.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.tpham8.expensemanager.R
import com.tpham8.expensemanager.databinding.MainFragmentBinding
import com.tpham8.expensemanager.MainActivity.Companion.BACKGROUND_COLOR


class MainFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    private var binding: MainFragmentBinding? = null

    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        var mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)
        binding = mainFragmentBinding
        binding?.apply {
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadUrl("https://www.fool.com/the-ascent/research/average-monthly-expenses/")
            startButton.setOnClickListener{
                findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
            }
        }

        return mainFragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when(key) {
            BACKGROUND_COLOR -> setColor()
        }
    }

    private fun setColor() {

    }
}