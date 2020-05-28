package com.alvin.moviecataloguejetpack.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @SerializedName("id")
    @Expose
    var id: Int?,

    @SerializedName("title")
    @Expose
    var title: String?,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String?,

    @SerializedName("runtime")
    @Expose
    var runtime: Int?,

    @SerializedName("vote_average")
    @Expose
    var rating: Double?,

    @SerializedName("genres")
    @Expose
    var genres: List<Genre>?,

    @SerializedName("overview")
    @Expose
    var overview: String?,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String?,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String?
)