package com.alvin.moviecataloguejetpack.data.source

import androidx.lifecycle.LiveData
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity

interface MovieDataSource {

    fun getMovies(page: Int): LiveData<List<MovieEntity>>

    fun getTvShows(page: Int): LiveData<List<MovieEntity>>

    fun getDetailMovie(movieId: Int): LiveData<DetailMovieEntity>

    fun getDetailTvShow(tvId: Int): LiveData<DetailMovieEntity>

}