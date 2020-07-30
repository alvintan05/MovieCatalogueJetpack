package com.alvin.moviecataloguejetpack.data.source.remote

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alvin.moviecataloguejetpack.BuildConfig
import com.alvin.moviecataloguejetpack.data.source.paging.MovieDataSourceFactory
import com.alvin.moviecataloguejetpack.data.source.paging.TvShowDataSourceFactory
import com.alvin.moviecataloguejetpack.data.source.remote.network.RetrofitServer
import com.alvin.moviecataloguejetpack.data.source.remote.response.*
import com.alvin.moviecataloguejetpack.utils.EspressoIdlingResource
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

    private val movieDataSourceFactory = MovieDataSourceFactory()
    private val tvDataSourceFactory = TvShowDataSourceFactory()

//    fun getMovies(page:Int, callback: LoadMoviesCallback) {
//        EspressoIdlingResource.increment()
//        RetrofitServer.getService().getMovies(BuildConfig.TMDB_API_KEY, page)
//            .enqueue(object : Callback<MoviesResponse> {
//                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
//
//                }
//
//                override fun onResponse(
//                    call: Call<MoviesResponse>,
//                    response: Response<MoviesResponse>
//                ) {
//                    response.body()?.results?.let { callback.onAllMoviesReceived(it) }
//                    EspressoIdlingResource.decrement()
//                }
//
//            })
//
//    }

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(10)
        .setPageSize(10)
        .build()

    fun getMovies(): LiveData<PagedList<Movie>> {
        return LivePagedListBuilder(movieDataSourceFactory, config).build()
    }

    fun getTvShows(): LiveData<PagedList<TvShow>>{
        return LivePagedListBuilder(tvDataSourceFactory, config).build()
    }

//    fun getTvShows(page: Int, callback: LoadTvShowsCallback) {
//        EspressoIdlingResource.increment()
//        RetrofitServer.getService().getTvShows(BuildConfig.TMDB_API_KEY, page)
//            .enqueue(object : Callback<TvShowsResponse> {
//                override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
//
//                }
//
//                override fun onResponse(
//                    call: Call<TvShowsResponse>,
//                    response: Response<TvShowsResponse>
//                ) {
//                    response.body()?.results?.let { callback.onAllTvShowsReceived(it) }
//                    EspressoIdlingResource.decrement()
//                }
//
//            })
//    }

    fun getDetailMovie(movieId: Int,callback: LoadDetailMovieCallback) {
        EspressoIdlingResource.increment()
        RetrofitServer.getService().getMovieDetail(movieId, BuildConfig.TMDB_API_KEY)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    response.body()?.let { callback.onDetailMovieReceived(it) }
                    EspressoIdlingResource.decrement()
                }

            })

    }

    fun getDetailTvShow(tvId: Int, callback: LoadDetailTvShowCallback) {
        EspressoIdlingResource.increment()
        RetrofitServer.getService().getTvShowDetail(tvId, BuildConfig.TMDB_API_KEY)
            .enqueue(object : Callback<DetailTvShowResponse> {
                override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<DetailTvShowResponse>,
                    response: Response<DetailTvShowResponse>
                ) {
                    response.body()?.let { callback.onDetailTvShowReceived(it) }
                    EspressoIdlingResource.decrement()
                }

            })

    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovie: DetailMovieResponse)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(detailTvShow: DetailTvShowResponse)
    }
}