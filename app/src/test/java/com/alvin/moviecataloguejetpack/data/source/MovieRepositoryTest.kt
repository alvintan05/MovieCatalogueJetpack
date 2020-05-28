package com.alvin.moviecataloguejetpack.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alvin.moviecataloguejetpack.data.source.remote.RemoteDataSource
import com.alvin.moviecataloguejetpack.utils.DataDummy
import com.alvin.moviecataloguejetpack.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val page = 1

    private val movieResponses = DataDummy.generateRemoteDummyMovies(page)
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShows(page)
    private val movieId = movieResponses[0].id!!
    private val tvId = tvShowResponses[0].id!!
    private val movieDetail = DataDummy.generateRemoteDummyMovieDetail(movieId)
    private val tvShowDetail = DataDummy.generateRemoteDummyTvShowDetail(tvId)


    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getMovies(eq(page), any())
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getMovies(page))
        verify(remote).getMovies(eq(page), any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.size)
    }

    @Test
    fun getTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getTvShows(eq(page), any())
        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getTvShows(page))
        verify(remote).getTvShows(eq(page), any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailMovieCallback)
                .onDetailMovieReceived(movieDetail)
            null
        }.`when`(remote).getDetailMovie(eq(movieId), any())
        val result = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieId))
        verify(remote).getDetailMovie(eq(movieId), any())
        assertNotNull(result)
        assertEquals(movieDetail.title, result.title)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailTvShowCallback)
                .onDetailTvShowReceived(tvShowDetail)
            null
        }.`when`(remote).getDetailTvShow(eq(tvId), any())
        val result = LiveDataTestUtil.getValue(movieRepository.getDetailTvShow(tvId))
        verify(remote).getDetailTvShow(eq(tvId), any())
        assertNotNull(result)
        assertEquals(tvShowDetail.title, result.title)
    }
}