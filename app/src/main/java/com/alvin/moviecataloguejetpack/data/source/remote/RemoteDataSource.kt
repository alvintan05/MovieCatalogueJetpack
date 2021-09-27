package com.alvin.moviecataloguejetpack.data.source.remote

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alvin.moviecataloguejetpack.BuildConfig
import com.alvin.moviecataloguejetpack.data.source.paging.MovieDataSourceFactory
import com.alvin.moviecataloguejetpack.data.source.paging.TvShowDataSourceFactory
import com.alvin.moviecataloguejetpack.data.source.remote.network.RetrofitServer
import com.alvin.moviecataloguejetpack.data.source.remote.response.DetailMovieResponse
import com.alvin.moviecataloguejetpack.data.source.remote.response.DetailTvShowResponse
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow
import com.alvin.moviecataloguejetpack.utils.EspressoIdlingResource

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    private val movieDataSourceFactory = MovieDataSourceFactory()
    private val tvDataSourceFactory = TvShowDataSourceFactory()

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(10)
        .setPageSize(10)
        .build()

    fun getMovies(): LiveData<PagedList<Movie>> {
        return LivePagedListBuilder(movieDataSourceFactory, config).build()
    }

    fun getTvShows(): LiveData<PagedList<TvShow>> {
        return LivePagedListBuilder(tvDataSourceFactory, config).build()
    }

    suspend fun getDetailMovie(movieId: Int, callback: LoadDetailMovieCallback) {
        EspressoIdlingResource.increment()
        val response = RetrofitServer.getService().getMovieDetail(movieId, BuildConfig.TMDB_API_KEY)
        if (response.isSuccessful) {
            response.body()?.let { callback.onDetailMovieReceived(it) }
            EspressoIdlingResource.decrement()
        } else {

        }
    }

    suspend fun getDetailTvShow(tvId: Int, callback: LoadDetailTvShowCallback) {
        EspressoIdlingResource.increment()

        val response = RetrofitServer.getService().getTvShowDetail(tvId, BuildConfig.TMDB_API_KEY)
        if (response.isSuccessful) {
            response.body()?.let { callback.onDetailTvShowReceived(it) }
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovie: DetailMovieResponse)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(detailTvShow: DetailTvShowResponse)
    }
}