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

    fun getSelectedMovieDetail(apiKey: String, type: Int): LiveData<DetailMovieEntity> {
        lateinit var movie: LiveData<DetailMovieEntity>
        when (type) {
            1 -> movie = movieRepository.getDetailMovie(movieId, apiKey)
            2 -> movie = movieRepository.getDetailTvShow(movieId, apiKey)
        }

        return movie
    }

}