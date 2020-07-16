package com.alvin.moviecataloguejetpack.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    var movieId: Int? = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null,

    @ColumnInfo(name = "runtime")
    var runtime: Int? = 0,

    @ColumnInfo(name = "rating")
    var rating: Double? = 0.0,

    @ColumnInfo(name = "genre")
    var genres: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null,

    @ColumnInfo(name = "type")
    var type: Int? = 0
)