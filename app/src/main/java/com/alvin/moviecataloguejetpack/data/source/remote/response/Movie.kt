package com.alvin.moviecataloguejetpack.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("title")
    @Expose
    val title: String?,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double?,

    @SerializedName("overview")
    @Expose
    val overview: String?,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String?
) : Parcelable