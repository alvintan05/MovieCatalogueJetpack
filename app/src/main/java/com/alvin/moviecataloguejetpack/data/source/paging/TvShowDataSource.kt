package com.alvin.moviecataloguejetpack.data.source.paging

import androidx.paging.PageKeyedDataSource
import com.alvin.moviecataloguejetpack.BuildConfig
import com.alvin.moviecataloguejetpack.data.source.remote.network.FIRST_PAGE
import com.alvin.moviecataloguejetpack.data.source.remote.network.RetrofitServer
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShowsResponse
import com.alvin.moviecataloguejetpack.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowDataSource: PageKeyedDataSource<Int, TvShow>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShow>
    ) {
        EspressoIdlingResource.increment()
        RetrofitServer.getService()
            .getTvShows(BuildConfig.TMDB_API_KEY, FIRST_PAGE)
            .enqueue(object: Callback<TvShowsResponse> {
                override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<TvShowsResponse>,
                    response: Response<TvShowsResponse>
                ) {
                    EspressoIdlingResource.decrement()
                    val tvList: List<TvShow>? = response.body()?.results
                    if (tvList != null) {
                        callback.onResult(tvList, null, FIRST_PAGE + 1)
                    }
                }

            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        EspressoIdlingResource.increment()
        RetrofitServer.getService()
            .getTvShows(BuildConfig.TMDB_API_KEY, params.key)
            .enqueue(object: Callback<TvShowsResponse> {
                override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<TvShowsResponse>,
                    response: Response<TvShowsResponse>
                ) {
                    EspressoIdlingResource.decrement()
                    val tvList: List<TvShow>? = response.body()?.results
                    if (tvList != null) {
                        callback.onResult(tvList,  params.key + 1)
                    }
                }

            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        TODO("Not yet implemented")
    }
}