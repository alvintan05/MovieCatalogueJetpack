package com.alvin.moviecataloguejetpack.data.source.local

data class MovieEntity(
    var movieId: Int,
    var title: String,
    var rating: Double,
    var overview: String,
    var posterPath: String
)