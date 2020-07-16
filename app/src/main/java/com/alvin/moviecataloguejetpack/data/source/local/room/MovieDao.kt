package com.alvin.moviecataloguejetpack.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE movie_id = :id AND type = :type")
    fun deleteMovie(id: Int, type: Int)

    @Query("SELECT * FROM favorite WHERE type = 1")
    fun getFavMovie(): LiveData<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE type = 2")
    fun getFavTvShow(): LiveData<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE movie_id = :id AND type = 1")
    fun getDetailFavoriteMovie(id: Int): LiveData<FavoriteEntity>

    @Query("SELECT * FROM favorite WHERE movie_id = :id AND type = 2")
    fun getDetailFavoriteTv(id: Int): LiveData<FavoriteEntity>

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE movie_id = :id AND type = :type)")
    fun getIsFavorite(id: Int, type: Int): LiveData<Boolean>
}