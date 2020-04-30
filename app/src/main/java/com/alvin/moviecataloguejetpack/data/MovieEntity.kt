package com.alvin.moviecataloguejetpack.data

data class MovieEntity(
    var movieId: Int,
    var title: String,
    var releaseDate: String,
    var runtime: Int,
    var rating: Double,
    var category: String,
    var overview: String,
    var posterPath: Int,
    var backdropPath: String
)