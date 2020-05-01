package com.alvin.moviecataloguejetpack.ui.detail

import com.alvin.moviecataloguejetpack.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {

    private lateinit var movieViewModel: DetailMovieViewModel
    private lateinit var tvShowViewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.movieId
    private val tvId = dummyTvShow.movieId

    @Before
    fun setup() {
        movieViewModel = DetailMovieViewModel()
        tvShowViewModel = DetailMovieViewModel()
        movieViewModel.setSelectedMovie(movieId)
        tvShowViewModel.setSelectedMovie(tvId)
    }

    @Test
    fun getSelectedMovieDetail() {
        movieViewModel.setSelectedMovie(dummyMovie.movieId)
        val movieEntity = movieViewModel.getSelectedMovieDetail(1)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.backdropPath, movieEntity.backdropPath)
        assertEquals(dummyMovie.runtime, movieEntity.runtime)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.category, movieEntity.category)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovie.rating, movieEntity.rating, 0.0001)
        assertEquals(dummyMovie.title, movieEntity.title)
    }

    @Test
    fun getSelectedTvShowDetail() {
        tvShowViewModel.setSelectedMovie(dummyTvShow.movieId)
        val tvShowEntity = tvShowViewModel.getSelectedMovieDetail(2)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.movieId, tvShowEntity.movieId)
        assertEquals(dummyTvShow.backdropPath, tvShowEntity.backdropPath)
        assertEquals(dummyTvShow.runtime, tvShowEntity.runtime)
        assertEquals(dummyTvShow.releaseDate, tvShowEntity.releaseDate)
        assertEquals(dummyTvShow.category, tvShowEntity.category)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.posterPath, tvShowEntity.posterPath)
        assertEquals(dummyTvShow.rating, tvShowEntity.rating, 0.0001)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
    }
}