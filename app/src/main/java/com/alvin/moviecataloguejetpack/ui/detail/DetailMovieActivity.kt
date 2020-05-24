package com.alvin.moviecataloguejetpack.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alvin.moviecataloguejetpack.BuildConfig
import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.utils.Url
import com.alvin.moviecataloguejetpack.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(collapse_toolbar_detail) {
            setExpandedTitleColor(ContextCompat.getColor(context, android.R.color.transparent))
        }

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailMovieViewModel::class.java]

        progress_bar.visibility = View.VISIBLE

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_ID, 0)
            val type = extras.getInt(EXTRA_TYPE, 0)
            if (movieId != 0 && type != 0) {
                viewModel.setSelectedMovie(movieId)
                viewModel.getSelectedMovieDetail(type).observe(this, Observer { setDetailMovie(it) })
            }

        }
    }

    private fun setDetailMovie(movie: DetailMovieEntity) {
        progress_bar.visibility = View.GONE
        collapse_toolbar_detail.title = movie.title
        tv_detail_title.text = movie.title
        tv_detail_date.text = resources.getString(R.string.release_time, movie.releaseDate)
        tv_detail_length.text = resources.getString(R.string.length, movie.runtime.toString())
        tv_detail_category.text = movie.category
        tv_detail_synopsis.text = movie.overview
        tv_detail_rating.text = movie.rating.toString()

        Glide.with(this)
            .load(Url.getUrlPoster(movie.posterPath))
            .apply(
                RequestOptions.placeholderOf(R.color.colorTextSecondary)
                    .error(R.color.colorTextSecondary)
            )
            .into(img_detail_poster)

        Glide.with(this)
            .load(Url.getUrlBackdrop(movie.backdropPath))
            .apply(
                RequestOptions.placeholderOf(R.color.colorTextSecondary)
                    .error(R.color.colorTextSecondary)
            )
            .into(img_detail_backdrop)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
