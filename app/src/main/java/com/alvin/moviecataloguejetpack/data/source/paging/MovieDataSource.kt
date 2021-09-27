package com.alvin.moviecataloguejetpack.data.source.paging

import androidx.paging.PageKeyedDataSource
import com.alvin.moviecataloguejetpack.BuildConfig
import com.alvin.moviecataloguejetpack.data.source.remote.network.RetrofitServer
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.utils.EspressoIdlingResource
import com.alvin.moviecataloguejetpack.vo.Resource
import com.alvin.moviecataloguejetpack.vo.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDataSource() : PageKeyedDataSource<Int, Movie>() {

    private val firstPage = RetrofitServer.FIRST_PAGE

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitServer.getService().getMovies(BuildConfig.TMDB_API_KEY, firstPage)
            if (response.isSuccessful) {
                EspressoIdlingResource.decrement()
                val movieList: List<Movie>? = response.body()?.results
                if (movieList != null) {
                    callback.onResult(movieList, null, firstPage + 1)
                }
            }
        }
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitServer.getService().getMovies(BuildConfig.TMDB_API_KEY, params.key)
            if (response.isSuccessful) {
                EspressoIdlingResource.decrement()
                val movieList: List<Movie>? = response.body()?.results
                if (movieList != null) {
                    callback.onResult(movieList, params.key + 1)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }
}