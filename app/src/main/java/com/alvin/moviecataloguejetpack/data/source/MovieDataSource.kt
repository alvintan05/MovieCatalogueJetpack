package com.alvin.moviecataloguejetpack.data.source

import androidx.lifecycle.LiveData
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow

interface MovieDataSource {

    fun getMovies(apiKey: String, page: Int): LiveData<List<MovieEntity>>

    fun getTvShows(apiKey: String, page: Int): LiveData<List<MovieEntity>>

    fun getDetailMovie(movieId: Int, apiKey: String): LiveData<DetailMovieEntity>

    fun getDetailTvShow(tvId: Int, apiKey: String): LiveData<DetailMovieEntity>

}