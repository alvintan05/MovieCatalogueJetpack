package com.alvin.moviecataloguejetpack.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.alvin.moviecataloguejetpack.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar_home)

        // set up tab and view pager
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager_home.adapter = sectionsPagerAdapter
        tabs_home.setupWithViewPager(view_pager_home)

    }
}
