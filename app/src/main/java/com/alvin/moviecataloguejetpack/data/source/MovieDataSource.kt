package com.alvin.moviecataloguejetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow

interface MovieDataSource {

    fun getMovies(page: Int): LiveData<List<MovieEntity>>

    fun getTvShows(page: Int): LiveData<List<MovieEntity>>

    fun getDetailMovie(movieId: Int): LiveData<DetailMovieEntity>

    fun getDetailTvShow(tvId: Int): LiveData<DetailMovieEntity>

}