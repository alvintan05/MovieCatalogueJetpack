package com.alvin.moviecataloguejetpack.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = DataDummy.generateDummyTvShows(1)
        val response = MutableLiveData<List<MovieEntity>>()
        response.value = dummyTvShows

        Mockito.`when`(movieRepository.getTvShows(1)).thenReturn(response)
        val tvShowEntities = viewModel.data.value
        Mockito.verify(movieRepository).getTvShows(1)

        assertNotNull(tvShowEntities)
        assertEquals(dummyTvShows.size, tvShowEntities?.size)

        viewModel.data.observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }
}