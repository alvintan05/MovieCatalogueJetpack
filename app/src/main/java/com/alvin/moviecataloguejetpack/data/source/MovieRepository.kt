package com.alvin.moviecataloguejetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.LocalDataSource
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity
import com.alvin.moviecataloguejetpack.data.source.remote.RemoteDataSource
import com.alvin.moviecataloguejetpack.data.source.remote.response.DetailMovieResponse
import com.alvin.moviecataloguejetpack.data.source.remote.response.DetailTvShowResponse
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow
import java.util.concurrent.ExecutorService

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val executorService: ExecutorService
) : MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            executorService: ExecutorService
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localDataSource, executorService)
            }
    }

    override fun getMovies(page: Int): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovies(page, object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.voteAverage,
                        response.overview,
                        response.posterPath
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })

        return movieResults
    }

    override fun getTvShows(page: Int): LiveData<List<MovieEntity>> {
        val tvShowResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getTvShows(page, object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShow>) {
                val tvShowList = ArrayList<MovieEntity>()
                for (response in tvShowResponses) {
                    val tvShow = MovieEntity(
                        response.id,
                        response.name,
                        response.voteAverage,
                        response.overview,
                        response.posterPath
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })

        return tvShowResults
    }

    override fun getDetailMovie(movieId: Int): LiveData<DetailMovieEntity> {
        val detailResult = MutableLiveData<DetailMovieEntity>()

        remoteDataSource.getDetailMovie(movieId,
            object : RemoteDataSource.LoadDetailMovieCallback {
                override fun onDetailMovieReceived(detailMovie: DetailMovieResponse) {
                    val movie = DetailMovieEntity(
                        detailMovie.id,
                        detailMovie.title,
                        detailMovie.releaseDate,
                        detailMovie.runtime,
                        detailMovie.rating,
                        detailMovie.genres?.joinToString(", ", "", ".", 3, "etc") {
                            it.name.toString()
                        },
                        detailMovie.overview,
                        detailMovie.posterPath,
                        detailMovie.backdropPath
                    )

                    detailResult.postValue(movie)
                }

            })

        return detailResult
    }

    override fun getDetailTvShow(tvId: Int): LiveData<DetailMovieEntity> {
        val detailResult = MutableLiveData<DetailMovieEntity>()

        remoteDataSource.getDetailTvShow(tvId,
            object : RemoteDataSource.LoadDetailTvShowCallback {
                override fun onDetailTvShowReceived(detailTvShow: DetailTvShowResponse) {
                    val tvShow = DetailMovieEntity(
                        detailTvShow.id,
                        detailTvShow.title,
                        detailTvShow.releaseDate,
                        detailTvShow.episodeRunTime?.average()?.toInt(),
                        detailTvShow.rating,
                        detailTvShow.genres?.joinToString(", ", "", ".", 3, "etc") {
                            it.name.toString()
                        },
                        detailTvShow.overview,
                        detailTvShow.posterPath,
                        detailTvShow.backdropPath
                    )

                    detailResult.postValue(tvShow)
                }

            })

        return detailResult
    }

    override fun getFavMovie(): LiveData<List<FavoriteEntity>> = localDataSource.getFavMovie()

    override fun getFavTv(): LiveData<List<FavoriteEntity>> = localDataSource.getFavTvShow()

    override fun getDetailFavMovie(id: Int): LiveData<FavoriteEntity> =
        localDataSource.getDetailFavMovie(id)

    override fun getDetailFavTv(id: Int): LiveData<FavoriteEntity> =
        localDataSource.getDetailFavTv(id)

    override fun getIsFavorite(id: Int, type: Int): LiveData<Boolean> =
        localDataSource.getIsFavorite(id, type)

    override fun addFavorite(favoriteEntity: FavoriteEntity) {
        executorService.execute { localDataSource.addFavorite(favoriteEntity) }
    }

    override fun deleteFavorite(id: Int, type: Int) {
        executorService.execute { localDataSource.deleteFavorite(id, type) }
    }

}