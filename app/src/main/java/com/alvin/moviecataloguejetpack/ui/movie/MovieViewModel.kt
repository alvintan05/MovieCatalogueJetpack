package com.alvin.moviecataloguejetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val data: LiveData<List<MovieEntity>> by lazy {
        movieRepository.getMovies(1)
    }

}