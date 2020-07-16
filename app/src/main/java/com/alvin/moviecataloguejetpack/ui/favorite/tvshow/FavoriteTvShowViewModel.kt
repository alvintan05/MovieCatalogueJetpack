package com.alvin.moviecataloguejetpack.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity

class FavoriteTvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val data: LiveData<List<FavoriteEntity>> by lazy {
        movieRepository.getFavTv()
    }

}