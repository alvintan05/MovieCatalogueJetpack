package com.alvin.moviecataloguejetpack.ui.movie

import com.alvin.moviecataloguejetpack.BuildConfig
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setup() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        `when`(movieRepository.getMovies(BuildConfig.TMDB_API_KEY, 1)).thenReturn()
        val movieEntities = viewModel.getMovies()
        assertNotNull(movieEntities)
        assertEquals(19, movieEntities.size)
    }
}