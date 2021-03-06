package com.tpham8.expensemanager.ui

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.tpham8.expensemanager.BuildConfig
import com.tpham8.expensemanager.MainActivity
import com.tpham8.expensemanager.R
import com.tpham8.expensemanager.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var binding: FragmentInfoBinding? = null

    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val infoFragmentBinding = FragmentInfoBinding.inflate(inflater, container, false)
        binding = infoFragmentBinding
        return infoFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            buildTimeTextView.text = BuildConfig.BUILD_TIME
            titleTextView.text = resources.getString(R.string.app_name)
            versionTextView.text = BuildConfig.VERSION_NAME
            copyrightTextView.text = resources.getString(R.string.copyright)

            when (prefs.getString(MainActivity.BACKGROUND_COLOR, "0")?.toInt()) {
                0 -> root.setBackgroundColor(Color.parseColor("#ffffff"))
                else -> root.setBackgroundColor(Color.parseColor("#d3e0ea"))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}