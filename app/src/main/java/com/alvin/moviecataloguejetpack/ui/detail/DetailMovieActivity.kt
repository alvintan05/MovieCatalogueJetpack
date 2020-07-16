package com.alvin.moviecataloguejetpack.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity
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

    private var movieEntity: DetailMovieEntity? = null
    private var type = 1
    private var movieId = 1
    private var favoriteEntity = FavoriteEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(collapse_toolbar_detail) {
            setExpandedTitleColor(ContextCompat.getColor(context, android.R.color.transparent))
        }

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailMovieViewModel::class.java]

        progress_bar.visibility = View.VISIBLE

        val extras = intent.extras
        if (extras != null) {
            movieId = extras.getInt(EXTRA_ID, 0)
            type = extras.getInt(EXTRA_TYPE, 0)
            if (movieId != 0 && type != 0) {
                viewModel.setSelectedMovie(movieId)
                viewModel.setType(type)

                viewModel.isFavorite.observe(this, Observer { status ->
                    btn_fav.isChecked = status
                    viewModel.getSelectedMovieDetail()
                        .observe(this, Observer {
                            movieEntity = it
                            setDetailMovie(it)
                        })
//                    if (status) {
//                        Log.d("DetailMovie", "Status: $status" )
//                        viewModel.getSelectedMovieDetailLocal().observe(this, Observer { local ->
//                            favoriteEntity = local
//                            setDetailMovie(
//                                DetailMovieEntity(
//                                    local.movieId,
//                                    local.title,
//                                    local.releaseDate,
//                                    local.runtime,
//                                    local.rating,
//                                    local.genres,
//                                    local.overview,
//                                    local.posterPath,
//                                    local.backdropPath
//                                )
//                            )
//                        })
//                    } else {
//                        Log.d("DetailMovie", "Status: $status" )
//                        viewModel.getSelectedMovieDetail()
//                            .observe(this, Observer {
//                                movieEntity = it
//                                setDetailMovie(it)
//                            })
//                    }
                })
            }

        }

        btn_fav.setOnClickListener {
            favoriteEntity.let {
                it.movieId = movieEntity?.movieId
                it.title = movieEntity?.title
                it.releaseDate = movieEntity?.releaseDate
                it.runtime = movieEntity?.runtime
                it.rating = movieEntity?.rating
                it.genres = movieEntity?.category
                it.overview = movieEntity?.overview
                it.posterPath = movieEntity?.posterPath
                it.backdropPath = movieEntity?.backdropPath
                it.type = type
            }

            if (btn_fav.isChecked) {
                viewModel.addFavorite(favoriteEntity)
                Toast.makeText(this, "Added Favorite", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.deleteFavorite(movieId, type)
                Toast.makeText(this, "Delete Favorite", Toast.LENGTH_SHORT).show()
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
            .load(movie.posterPath?.let { Url.getUrlPoster(it) })
            .apply(
                RequestOptions.placeholderOf(R.color.colorTextSecondary)
                    .error(R.color.colorTextSecondary)
            )
            .into(img_detail_poster)

        Glide.with(this)
            .load(movie.backdropPath?.let { Url.getUrlBackdrop(it) })
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
