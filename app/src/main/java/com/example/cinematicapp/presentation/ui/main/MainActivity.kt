package com.example.cinematicapp.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BottomNavigationSource {
    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy { (supportFragmentManager.findFragmentById(R.id.containerView) as NavHostFragment).navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setupWithNavController(navController)
        bottomNavigationController()
    }

    override fun hideBottomNavigation(isvisible: Boolean) {
        if (isvisible) binding.bottomNavigationView.visibility = View.VISIBLE
        else binding.bottomNavigationView.visibility = View.GONE
    }

    private fun bottomNavigationController() = with(binding) {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> navController.navigate(R.id.graph_home)
                R.id.library -> navController.navigate(R.id.graph_library)
                R.id.watchLater -> navController.navigate(R.id.graph_watch_later)
                R.id.profile -> navController.navigate(R.id.graph_profile)
            }
            true
        }
    }
}