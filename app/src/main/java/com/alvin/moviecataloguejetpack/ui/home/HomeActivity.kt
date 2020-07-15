package com.alvin.moviecataloguejetpack.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.alvin.moviecataloguejetpack.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar_home)

        val navController = findNavController(R.id.fragment)
        bottom_nav_home.setupWithNavController(navController)

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
