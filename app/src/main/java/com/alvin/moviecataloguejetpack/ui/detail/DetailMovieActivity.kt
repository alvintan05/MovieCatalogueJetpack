package com.alvin.moviecataloguejetpack.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity
import com.alvin.moviecataloguejetpack.databinding.ActivityDetailMovieBinding
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

    private lateinit var binding: ActivityDetailMovieBinding
    private var movieEntity: DetailMovieEntity? = null
    private var type = 1
    private var movieId = 1
    private var favoriteEntity = FavoriteEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding.collapseToolbarDetail) {
            setExpandedTitleColor(ContextCompat.getColor(context, android.R.color.transparent))
        }

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailMovieViewModel::class.java]

        binding.progressBar.visibility = View.VISIBLE

        val extras = intent.extras
        if (extras != null) {
            movieId = extras.getInt(EXTRA_ID, 0)
            type = extras.getInt(EXTRA_TYPE, 0)
            if (movieId != 0 && type != 0) {
                viewModel.setSelectedMovie(movieId)
                viewModel.setType(type)

                viewModel.isFavorite.observe(this, Observer { status ->
                    binding.btnFav.isChecked = status
                    viewModel.getSelectedMovieDetail()
                        .observe(this, Observer {
                            movieEntity = it
                            setDetailMovie(it)
                        })
                })
            }

        }

        binding.btnFav.setOnClickListener {
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

            if (binding.btnFav.isChecked) {
                viewModel.addFavorite(favoriteEntity)
                Toast.makeText(this, "Added Favorite", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.deleteFavorite(movieId, type)
                Toast.makeText(this, "Delete Favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setDetailMovie(movie: DetailMovieEntity) {
        binding.progressBar.visibility = View.GONE
        binding.collapseToolbarDetail.title = movie.title
        binding.tvDetailTitle.text = movie.title
        binding.tvDetailDate.text = resources.getString(R.string.release_time, movie.releaseDate)
        binding.tvDetailLength.text = resources.getString(R.string.length, movie.runtime.toString())
        binding.tvDetailCategory.text = movie.category
        binding.tvDetailSynopsis.text = movie.overview
        binding.tvDetailRating.text = movie.rating.toString()

        Glide.with(this)
            .load(movie.posterPath?.let { Url.getUrlPoster(it) })
            .apply(
                RequestOptions.placeholderOf(R.color.colorTextSecondary)
                    .error(R.color.colorTextSecondary)
            )
            .into(binding.imgDetailPoster)

        Glide.with(this)
            .load(movie.backdropPath?.let { Url.getUrlBackdrop(it) })
            .apply(
                RequestOptions.placeholderOf(R.color.colorTextSecondary)
                    .error(R.color.colorTextSecondary)
            )
            .into(binding.imgDetailBackdrop)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
