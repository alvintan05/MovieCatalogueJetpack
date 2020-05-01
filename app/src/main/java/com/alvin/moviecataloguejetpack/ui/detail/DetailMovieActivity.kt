package com.alvin.moviecataloguejetpack.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.alvin.moviecataloguejetpack.R
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        setSupportActionBar(toolbar_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(collapse_toolbar_profile) {
            title = "Detail Movie"
            setExpandedTitleColor(ContextCompat.getColor(context, android.R.color.transparent))
        }
    }
}
