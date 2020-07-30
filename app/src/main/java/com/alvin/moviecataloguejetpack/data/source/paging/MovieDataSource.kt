package com.alvin.moviecataloguejetpack.data.source.paging

import androidx.paging.PageKeyedDataSource
import com.alvin.moviecataloguejetpack.BuildConfig
import com.alvin.moviecataloguejetpack.data.source.remote.network.FIRST_PAGE
import com.alvin.moviecataloguejetpack.data.source.remote.network.RetrofitServer
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.data.source.remote.response.MoviesResponse
import com.alvin.moviecataloguejetpack.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource() : PageKeyedDataSource<Int, Movie>() {

    private var page = FIRST_PAGE

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        EspressoIdlingResource.increment()
        RetrofitServer.getService()
            .getMovies(BuildConfig.TMDB_API_KEY, page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    EspressoIdlingResource.decrement()
                    val movieList: List<Movie>? = response.body()?.results
                    if (movieList != null) {
                        callback.onResult(movieList, null, page + 1)
                    }
                }

            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        EspressoIdlingResource.increment()
        RetrofitServer.getService()
            .getMovies(BuildConfig.TMDB_API_KEY, params.key)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    EspressoIdlingResource.decrement()
                    val movieList: List<Movie>? = response.body()?.results
                    if (movieList != null) {
                        callback.onResult(movieList, params.key + 1)
                    }
                }

            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }
}