package com.example.cinematicapp.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationSource {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navController = (supportFragmentManager.findFragmentById(R.id.containerView) as NavHostFragment).navController
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun hideSearchMenu(visibility: Boolean) {
        binding.imageView.isVisible = visibility
        binding.cardView.isVisible = visibility
    }

    override fun hideBottomMenu(visibility:Boolean) {
        binding.bottomNavigationView.isVisible = visibility
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}