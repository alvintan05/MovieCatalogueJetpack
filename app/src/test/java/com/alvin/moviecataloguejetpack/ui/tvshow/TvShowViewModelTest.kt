package com.alvin.moviecataloguejetpack.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alvin.moviecataloguejetpack.data.source.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow
import com.alvin.moviecataloguejetpack.utils.DataDummy
import com.alvin.moviecataloguejetpack.utils.PagedListUtil
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
    private lateinit var observer: Observer<PagedList<TvShow>>

    @Mock
    private lateinit var pagedList: PagedList<TvShow>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = pagedList
        val response = MutableLiveData<PagedList<TvShow>>()
        response.value = dummyTvShows

        Mockito.`when`(movieRepository.getTvShows()).thenReturn(response)
        val tvShowEntities = viewModel.data.value
        Mockito.verify(movieRepository).getTvShows()

        assertNotNull(tvShowEntities)
        assertEquals(dummyTvShows.size, tvShowEntities?.size)

        viewModel.data.observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }
}