package com.yasin.licious

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.yasin.licious.databinding.ActivityHomeBinding

/**
 * Created by Yasin on 26/4/20.
 * The Main Activity Class
 **/

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        addDestinationChangeListener()
    }

    /**
     * Since we have set custom toolbar as ActionBar, change the title of toolbar as
     * destination changes
     **/
    private fun addDestinationChangeListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.title.text = destination.label
        }
    }
}
