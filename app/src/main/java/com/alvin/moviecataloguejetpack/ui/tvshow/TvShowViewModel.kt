package com.alvin.moviecataloguejetpack.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val data: LiveData<PagedList<TvShow>> by lazy {
        movieRepository.getTvShows()
    }
}