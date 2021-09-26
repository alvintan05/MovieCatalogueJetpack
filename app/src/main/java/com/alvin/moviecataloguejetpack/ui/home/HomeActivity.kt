package com.alvin.moviecataloguejetpack.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.databinding.ActivityHomeBinding
import com.facebook.stetho.Stetho

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Stetho.initializeWithDefaults(this)

        setSupportActionBar(binding.toolbarHome)

        val navController = findNavController(R.id.fragment)
        binding.bottomNavHome.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.movieFragment,
                R.id.tvShowFragment,
                R.id.favoriteFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
