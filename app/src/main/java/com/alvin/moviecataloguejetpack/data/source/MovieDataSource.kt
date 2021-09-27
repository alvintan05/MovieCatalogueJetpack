package com.alvin.moviecataloguejetpack.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow
import com.alvin.moviecataloguejetpack.vo.Resource

interface MovieDataSource {

    suspend fun getMovies(): LiveData<PagedList<Movie>>

    suspend fun getTvShows(): LiveData<PagedList<TvShow>>

    suspend fun getDetailMovie(movieId: Int): DetailMovieEntity

    suspend fun getDetailTvShow(tvId: Int): DetailMovieEntity

    fun getFavMovie(): LiveData<List<FavoriteEntity>>

    fun getFavTv(): LiveData<List<FavoriteEntity>>

    fun getDetailFavMovie(id: Int): LiveData<FavoriteEntity>

    fun getDetailFavTv(id: Int): LiveData<FavoriteEntity>

    fun getIsFavorite(id: Int, type: Int): LiveData<Boolean>

    fun addFavorite(favoriteEntity: FavoriteEntity)

    fun deleteFavorite(id: Int, type: Int)
}