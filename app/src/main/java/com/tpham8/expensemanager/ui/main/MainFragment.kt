package com.tpham8.expensemanager.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.tpham8.expensemanager.R
import com.tpham8.expensemanager.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var binding: MainFragmentBinding? = null

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

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}