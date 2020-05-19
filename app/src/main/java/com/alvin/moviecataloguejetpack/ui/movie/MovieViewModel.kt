package com.alvin.moviecataloguejetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies(apiKey: String, page: Int): LiveData<List<MovieEntity>> =
        movieRepository.getMovies(apiKey, page)
}