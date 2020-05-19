package com.alvin.moviecataloguejetpack.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.utils.DataDummy

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTvShows(apiKey: String, page: Int): LiveData<List<MovieEntity>> =
        movieRepository.getTvShows(apiKey, page)
}