package com.alvin.moviecataloguejetpack.ui.detail

import androidx.lifecycle.ViewModel
import com.alvin.moviecataloguejetpack.data.MovieEntity
import com.alvin.moviecataloguejetpack.utils.DataDummy

class DetailMovieViewModel : ViewModel() {

    private var movieId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getSelectedMovieDetail(type: Int): MovieEntity {
        lateinit var movie: MovieEntity
        lateinit var movieEntities: List<MovieEntity>
        when (type) {
            1 -> movieEntities = DataDummy.generateDummyMovies()
            2 -> movieEntities = DataDummy.generateDummyTvShows()
        }

        for (movieEntity in movieEntities) {
            if (movieEntity.movieId == movieId) {
                movie = movieEntity
            }
        }

        return movie
    }

}