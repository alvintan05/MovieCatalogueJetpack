package com.alvin.moviecataloguejetpack.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.utils.DataDummy
import com.alvin.moviecataloguejetpack.utils.PagedListUtil
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
    private lateinit var observer: Observer<PagedList<Movie>>

    @Mock
    private lateinit var pagedList: PagedList<Movie>

    @Before
    fun setup() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = pagedList
        val response = MutableLiveData<PagedList<Movie>>()
        response.value = dummyMovies

        `when`(movieRepository.getMovies()).thenReturn(response as LiveData<PagedList<Movie>>)
        val movieEntities = viewModel.data.value
        verify(movieRepository).getMovies()
        assertNotNull(movieEntities)
        assertEquals(dummyMovies.size, movieEntities?.size)

        viewModel.data.observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}