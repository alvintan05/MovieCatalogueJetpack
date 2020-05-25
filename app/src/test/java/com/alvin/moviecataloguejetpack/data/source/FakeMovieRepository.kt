package com.alvin.moviecataloguejetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.remote.RemoteDataSource
import com.alvin.moviecataloguejetpack.data.source.remote.response.DetailMovieResponse
import com.alvin.moviecataloguejetpack.data.source.remote.response.DetailTvShowResponse
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {

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
                        detailMovie.genres[0].name,
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
                        detailTvShow.episodeRunTime[0],
                        detailTvShow.rating,
                        detailTvShow.genres[0].name,
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