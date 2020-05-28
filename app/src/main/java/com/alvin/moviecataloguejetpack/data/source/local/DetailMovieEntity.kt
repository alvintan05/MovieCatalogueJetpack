package com.alvin.moviecataloguejetpack.data.source.local

data class DetailMovieEntity(
    var movieId: Int?,
    var title: String?,
    var releaseDate: String?,
    var runtime: Int?,
    var rating: Double?,
    var category: String?,
    var overview: String?,
    var posterPath: String?,
    var backdropPath: String?
)