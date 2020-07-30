package com.alvin.moviecataloguejetpack.data.source.remote.network

import com.alvin.moviecataloguejetpack.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

object RetrofitServer {

    private fun getInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getService(): ApiRequest = getInstance().create(ApiRequest::class.java)

}