package com.alvin.moviecataloguejetpack.utils

import com.alvin.moviecataloguejetpack.BuildConfig

object Url {

    private const val posterUrl = BuildConfig.IMAGE_BASE_URL + "w342"
    private const val backdropUrl = BuildConfig.IMAGE_BASE_URL + "w780"

    fun getUrlPoster(path: String): String = posterUrl + path

    fun getUrlBackdrop(path: String): String = backdropUrl + path
}