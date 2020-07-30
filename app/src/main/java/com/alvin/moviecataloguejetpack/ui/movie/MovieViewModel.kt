package com.alvin.moviecataloguejetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val data: LiveData<PagedList<Movie>> by lazy {
        movieRepository.getMovies()
    }

}