package com.alvin.moviecataloguejetpack.ui.movie

import androidx.lifecycle.ViewModel
import com.alvin.moviecataloguejetpack.data.MovieEntity
import com.alvin.moviecataloguejetpack.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()
}