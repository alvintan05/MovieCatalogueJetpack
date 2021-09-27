package com.alvin.moviecataloguejetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.alvin.moviecataloguejetpack.data.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    lateinit var data: LiveData<PagedList<Movie>>

    init {
        getData()
    }

    fun getData() = viewModelScope.launch {
        val result = movieRepository.getMovies()
        data = result
    }

}