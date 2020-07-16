package com.alvin.moviecataloguejetpack.data.source

import androidx.lifecycle.LiveData
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity

interface MovieDataSource {

    fun getMovies(page: Int): LiveData<List<MovieEntity>>

    fun getTvShows(page: Int): LiveData<List<MovieEntity>>

    fun getDetailMovie(movieId: Int): LiveData<DetailMovieEntity>

    fun getDetailTvShow(tvId: Int): LiveData<DetailMovieEntity>

    fun getFavMovie(): LiveData<List<FavoriteEntity>>

    fun getFavTv(): LiveData<List<FavoriteEntity>>

    fun getDetailFavMovie(id: Int): LiveData<FavoriteEntity>

    fun getDetailFavTv(id: Int): LiveData<FavoriteEntity>

    fun getIsFavorite(id: Int, type: Int): LiveData<Boolean>

    fun addFavorite(favoriteEntity: FavoriteEntity)

    fun deleteFavorite(id: Int, type: Int)
}