package com.alvin.moviecataloguejetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var movieId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getSelectedMovieDetail(type: Int): LiveData<DetailMovieEntity> {
        lateinit var movie: LiveData<DetailMovieEntity>
        when (type) {
            1 -> movie = dataMovie
            2 -> movie = dataTvSHow
        }
        return movie
    }

    val dataMovie: LiveData<DetailMovieEntity> by lazy {
        movieRepository.getDetailMovie(movieId)
    }

    val dataTvSHow: LiveData<DetailMovieEntity> by lazy {
        movieRepository.getDetailTvShow(movieId)
    }

}