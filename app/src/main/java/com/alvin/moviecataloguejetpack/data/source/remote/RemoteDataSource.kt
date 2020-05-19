package com.alvin.moviecataloguejetpack.data.source.remote

import com.alvin.moviecataloguejetpack.data.source.remote.network.RetrofitServer
import com.alvin.moviecataloguejetpack.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovies(apiKey: String, page: Int, callback: LoadMoviesCallback) {

        RetrofitServer.getService().getMovies(apiKey, page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    response.body()?.results?.let { callback.onAllMoviesReceived(it) }
                }

            })
    }

    fun getTvShows(apiKey: String, page: Int, callback: LoadTvShowsCallback) {

        RetrofitServer.getService().getTvShows(apiKey, page)
            .enqueue(object : Callback<TvShowsResponse> {
                override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<TvShowsResponse>,
                    response: Response<TvShowsResponse>
                ) {
                    response.body()?.results?.let { callback.onAllTvShowsReceived(it) }
                }

            })
    }

    fun getDetailMovie(movieId: Int, apiKey: String, callback: LoadDetailMovieCallback) {

        RetrofitServer.getService().getMovieDetail(movieId, apiKey)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    response.body()?.let { callback.onDetailMovieReceived(it) }
                }

            })

    }

    fun getDetailTvShow(tvId: Int, apiKey: String, callback: LoadDetailTvShowCallback) {

        RetrofitServer.getService().getTvShowDetail(tvId, apiKey)
            .enqueue(object : Callback<DetailTvShowResponse> {
                override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<DetailTvShowResponse>,
                    response: Response<DetailTvShowResponse>
                ) {
                    response.body()?.let { callback.onDetailTvShowReceived(it) }
                }

            })

    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<Movie>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponses: List<TvShow>)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovie: DetailMovieResponse)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(detailTvShow: DetailTvShowResponse)
    }
}