package com.alvin.moviecataloguejetpack.data.source.local

import androidx.lifecycle.LiveData
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity
import com.alvin.moviecataloguejetpack.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getFavMovie(): LiveData<List<FavoriteEntity>> = movieDao.getFavMovie()

    fun getFavTvShow(): LiveData<List<FavoriteEntity>> = movieDao.getFavTvShow()

    fun getDetailFavMovie(id: Int): LiveData<FavoriteEntity> = movieDao.getDetailFavoriteMovie(id)

    fun getDetailFavTv(id: Int): LiveData<FavoriteEntity> = movieDao.getDetailFavoriteTv(id)

    fun addFavorite(favoriteEntity: FavoriteEntity) = movieDao.insertMovie(favoriteEntity)

    fun deleteFavorite(id: Int, type: Int) = movieDao.deleteMovie(id, type)

    fun getIsFavorite(id: Int, type: Int): LiveData<Boolean> = movieDao.getIsFavorite(id, type)
}