package com.alvin.moviecataloguejetpack.data.source.paging

import androidx.paging.PageKeyedDataSource
import com.alvin.moviecataloguejetpack.BuildConfig
import com.alvin.moviecataloguejetpack.data.source.remote.network.RetrofitServer
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow
import com.alvin.moviecataloguejetpack.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowDataSource : PageKeyedDataSource<Int, TvShow>() {

    private val firstPage = RetrofitServer.FIRST_PAGE

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShow>
    ) {
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitServer.getService().getTvShows(BuildConfig.TMDB_API_KEY, firstPage)
            if (response.isSuccessful) {
                EspressoIdlingResource.decrement()
                val tvList: List<TvShow>? = response.body()?.results
                if (tvList != null) {
                    callback.onResult(tvList, null, RetrofitServer.FIRST_PAGE + 1)
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitServer.getService().getTvShows(BuildConfig.TMDB_API_KEY, params.key)
            if (response.isSuccessful) {
                EspressoIdlingResource.decrement()
                val tvList: List<TvShow>? = response.body()?.results
                if (tvList != null) {
                    callback.onResult(tvList, params.key + 1)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        TODO("Not yet implemented")
    }
}