package com.alvin.moviecataloguejetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alvin.moviecataloguejetpack.data.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var movieId: Int = 0
    private var type: Int = 1

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun setType(type: Int) {
        this.type = type
    }

    fun getSelectedMovieDetail(): LiveData<DetailMovieEntity> {
        lateinit var movie: LiveData<DetailMovieEntity>
        when (type) {
            1 -> movie = dataMovie
            2 -> movie = dataTvSHow
        }
        return movie
    }

    fun getSelectedMovieDetailLocal(): LiveData<FavoriteEntity> {
        lateinit var movie: LiveData<FavoriteEntity>
        when (type) {
            1 -> movie = localMovie
            2 -> movie = localTv
        }
        return movie
    }

    val dataMovie: LiveData<DetailMovieEntity> by lazy {
        movieRepository.getDetailMovie(movieId)
    }

    val dataTvSHow: LiveData<DetailMovieEntity> by lazy {
        movieRepository.getDetailTvShow(movieId)
    }

    val localMovie: LiveData<FavoriteEntity> by lazy{
        movieRepository.getDetailFavMovie(movieId)
    }

    val localTv: LiveData<FavoriteEntity> by lazy {
        movieRepository.getDetailFavTv(movieId)
    }

    val isFavorite: LiveData<Boolean> by lazy {
        movieRepository.getIsFavorite(movieId, type)
    }

    fun addFavorite(favoriteEntity: FavoriteEntity) {
        movieRepository.addFavorite(favoriteEntity)
    }

    fun deleteFavorite(id: Int, type: Int) {
        movieRepository.deleteFavorite(id, type)
    }

}