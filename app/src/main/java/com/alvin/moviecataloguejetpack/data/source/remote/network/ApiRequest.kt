package com.alvin.moviecataloguejetpack.data.source.remote.network

import com.alvin.moviecataloguejetpack.data.source.remote.response.DetailMovieResponse
import com.alvin.moviecataloguejetpack.data.source.remote.response.DetailTvShowResponse
import com.alvin.moviecataloguejetpack.data.source.remote.response.MoviesResponse
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<MoviesResponse>

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<TvShowsResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailMovieResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailTvShowResponse>
}