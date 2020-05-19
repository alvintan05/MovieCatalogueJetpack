package com.alvin.moviecataloguejetpack.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvShowsResponse (
    @SerializedName("page")
    @Expose
    val page: Int? = null,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int? = null,

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = null,

    @SerializedName("results")
    @Expose
    val results: List<TvShow>? = null
)