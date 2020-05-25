package com.alvin.moviecataloguejetpack.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.remote.network.ApiRequest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var apiRequest: ApiRequest

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setup() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {

        `when`(movieRepository.getMovies(1)).thenReturn()
        val movieEntities = viewModel
        verify(movieRepository).getMovies(1)
        assertNotNull(movieEntities)
        assertEquals(19, movieEntities.size)

        viewModel.data.observeForever(observer)
        verify(observer).onChanged()
    }
}