package com.alvin.moviecataloguejetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.remote.RemoteDataSource
import com.alvin.moviecataloguejetpack.data.source.remote.response.*

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData)
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
                        detailMovie.genres.joinToString(", ", "", ".", 3, "etc") {
                            it.name
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
                        detailTvShow.episodeRunTime.average().toInt(),
                        detailTvShow.rating,
                        detailTvShow.genres.joinToString(", ", "", ".", 3, "etc") {
                            it.name
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
}