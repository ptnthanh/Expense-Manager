package com.tpham8.expensemanager

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
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
        setTheme(R.style.BlueTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.let {
                it.title = when (destination.id) {
                    R.id.settingsFragment -> getString(R.string.settings)
                    R.id.infoFragment -> getString(R.string.info)
                    else -> getString(R.string.app_name)
                }
            }
        }

        if (savedInstanceState == null) {
            if (prefs.getBoolean(SHOW_MESSAGE_AT_START, false)) {
                welcomeAlert()
            }
            when (prefs.getString(CHOSEN_THEME, "0")?.toInt()) {
                0 -> setTheme(R.style.BlueTheme)
                else -> setTheme(R.style.YellowTheme)
            }
        }

    }

    private fun welcomeAlert() {
        val msg = resources.getString(R.string.welcome)
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(R.string.hello)
            setMessage(msg)
            setIcon(R.drawable.app_icon)
            setPositiveButton(R.string.ok, null)
            show()
        }
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.navHostFragment).navigateUp()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                navHostFragment.navController.navigate(R.id.action_mainFragment_to_settingsFragment)
                true
            }
            R.id.action_info -> {
                navHostFragment.navController.navigate(R.id.action_mainFragment_to_infoFragment)
                true
            }
            R.id.action_reset -> {
                with(prefs.edit()) {
                    remove(SHOW_MESSAGE_AT_START)
                    remove(BACKGROUND_COLOR)
                    remove(CHOSEN_THEME)
                    apply()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val SHOW_MESSAGE_AT_START = "show_message_at_start"
        const val BACKGROUND_COLOR = "background_color"
        const val CHOSEN_THEME = "chosen_theme"
    }
}