package com.example.cinematicapp.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.ActivityMainBinding
import com.example.cinematicapp.presentation.adapters.mainRcView.MainRcViewAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), MainSource {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var adapter: MainRcViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navController = (supportFragmentManager.findFragmentById(R.id.containerView) as NavHostFragment).navController
        bottomNavigationView.setupWithNavController(navController)
        initRc()
        setupListener()
    }

    private fun initRc() {
        adapter = MainRcViewAdapter {
            val string = getString(it.name)
            Toast.makeText(applicationContext, "${string}", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerViewMain.adapter = adapter
    }

    private fun setupListener() = with(binding) {
        imageView.setOnClickListener {
            searchListener()
        }
    }

    override fun hideSearchMenu(visibility: Boolean) {
        binding.searchContainer.isVisible = visibility
    }

    override fun hideBottomMenu(visibility:Boolean) {
        binding.bottomNavigationView.isVisible = visibility
    }

    override fun searchListener(): Array<String> {
        val searchText = binding.edSearchText.text.toString()
        return arrayOf(searchText)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}