package com.alvin.moviecataloguejetpack.data.source.local

data class DetailMovieEntity(
    var movieId: Int? = null,
    var title: String? = null,
    var releaseDate: String? = null,
    var runtime: Int? = null,
    var rating: Double? = null,
    var category: String? = null,
    var overview: String? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null
)