package com.alvin.moviecataloguejetpack.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var movieViewModel: DetailMovieViewModel
    private lateinit var tvShowViewModel: DetailMovieViewModel

    private val movieId = DataDummy.generateDummyMovies(1)[0].movieId!!
    private val tvId = DataDummy.generateDummyTvShows(1)[0].movieId!!

    private val dummyMovie = DataDummy.generateDummyMovieDetail(movieId)
    private val dummyTvShow = DataDummy.generateDummyTvShowDetail(tvId)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<DetailMovieEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<DetailMovieEntity>

    @Before
    fun setup() {
        movieViewModel = DetailMovieViewModel(movieRepository)
        tvShowViewModel = DetailMovieViewModel(movieRepository)
        movieViewModel.setSelectedMovie(movieId)
        tvShowViewModel.setSelectedMovie(tvId)
    }

    @Test
    fun getSelectedMovieDetail() {
        val movie = MutableLiveData<DetailMovieEntity>()
        movie.value = dummyMovie

        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(movie)
        movieViewModel.getSelectedMovieDetail(1)
        val movieEntity = movieViewModel.dataMovie.value as DetailMovieEntity

        verify(movieRepository).getDetailMovie(movieId)

        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.backdropPath, movieEntity.backdropPath)
        assertEquals(dummyMovie.runtime, movieEntity.runtime)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.category, movieEntity.category)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovie.rating!!, movieEntity.rating!!, 0.0001)
        assertEquals(dummyMovie.title, movieEntity.title)

        movieViewModel.dataMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getSelectedTvShowDetail() {
        val tvShow = MutableLiveData<DetailMovieEntity>()
        tvShow.value = dummyTvShow

        `when`(movieRepository.getDetailTvShow(tvId)).thenReturn(tvShow)
        tvShowViewModel.getSelectedMovieDetail(2)
        val tvShowEntity = tvShowViewModel.dataTvSHow.value as DetailMovieEntity

        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.movieId, tvShowEntity.movieId)
        assertEquals(dummyTvShow.backdropPath, tvShowEntity.backdropPath)
        assertEquals(dummyTvShow.runtime, tvShowEntity.runtime)
        assertEquals(dummyTvShow.releaseDate, tvShowEntity.releaseDate)
        assertEquals(dummyTvShow.category, tvShowEntity.category)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.posterPath, tvShowEntity.posterPath)
        assertEquals(dummyTvShow.rating!!, tvShowEntity.rating!!, 0.0001)
        assertEquals(dummyTvShow.title, tvShowEntity.title)

        tvShowViewModel.dataTvSHow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}