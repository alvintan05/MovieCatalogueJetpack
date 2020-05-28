package com.alvin.moviecataloguejetpack.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailTvShowResponse(

    @SerializedName("id")
    @Expose
    var id: Int?,

    @SerializedName("name")
    @Expose
    var title: String?,

    @SerializedName("first_air_date")
    @Expose
    var releaseDate: String?,

    @SerializedName("episode_run_time")
    @Expose
    var episodeRunTime: List<Int>?,

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