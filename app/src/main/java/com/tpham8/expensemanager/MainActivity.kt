package com.tpham8.expensemanager

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.tpham8.expensemanager.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupActionBarWithNavController(
            this, navHostFragment.navController
        )

    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.navHostFragment).navigateUp()

}