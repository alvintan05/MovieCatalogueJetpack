package com.alvin.moviecataloguejetpack.data.source.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie


class MovieDataSourceFactory : DataSource.Factory<Int, Movie>() {

    private val moviesLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource()
        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }

    fun getMovies() = moviesLiveDataSource
}