package com.alvin.moviecataloguejetpack.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.alvin.moviecataloguejetpack.data.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow
import kotlinx.coroutines.launch

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    lateinit var data: LiveData<PagedList<TvShow>>

    init {
        getData()
    }

    fun getData() = viewModelScope.launch {
        val result = movieRepository.getTvShows()
        data = result
    }
}