package com.alvin.moviecataloguejetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvin.moviecataloguejetpack.data.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.launch

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
            1 -> {
                getDataMovie()
                movie = dataMovie
            }
            2 -> {
                getDataTvShow()
                movie = dataTvShow
            }
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

    val dataMovie: LiveData<DetailMovieEntity> get() = mDataMovie

    private val mDataMovie = MutableLiveData<DetailMovieEntity>()

    fun getDataMovie() {
        viewModelScope.launch {
            mDataMovie.value = movieRepository.getDetailMovie(movieId)
        }
    }

    val dataTvShow: LiveData<DetailMovieEntity> get() = mDataTvShow

    private val mDataTvShow = MutableLiveData<DetailMovieEntity>()

    fun getDataTvShow() {
        viewModelScope.launch {
            mDataTvShow.value = movieRepository.getDetailTvShow(movieId)
        }
    }

    val localMovie: LiveData<FavoriteEntity> by lazy {
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