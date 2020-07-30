package com.alvin.moviecataloguejetpack.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.LocalDataSource
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity
import com.alvin.moviecataloguejetpack.data.source.remote.RemoteDataSource
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.utils.DataDummy
import com.alvin.moviecataloguejetpack.utils.LiveDataTestUtil
import com.alvin.moviecataloguejetpack.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.concurrent.ExecutorService

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val executor = mock(ExecutorService::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, executor)

    private val page = 1

    private val movieResponses = DataDummy.generateRemoteDummyMovies(page)
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShows(page)
    private val movieId = movieResponses[0].id!!
    private val tvId = tvShowResponses[0].id!!
    private val movieDetail = DataDummy.generateRemoteDummyMovieDetail(movieId)
    private val tvShowDetail = DataDummy.generateRemoteDummyTvShowDetail(tvId)

//    @Test
//    fun getMovies() {
//        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
//        `when`(remote.getMovies()).thenReturn(dataSourceFactory)
//        movieRepository.getMovies()
//
//        val movieEntities = PagedListUtil.mockPagedList(movieResponses)
//        verify(remote).getMovies()
//        assertNotNull(movieEntities)
//        assertEquals(movieResponses.size, movieEntities.size)
//
//        doAnswer { invocation ->
//            (invocation.arguments[1] as RemoteDataSource.LoadMoviesCallback)
//                .onAllMoviesReceived(movieResponses)
//            null
//        }.`when`(remote).getMovies(eq(page), any())
//
//        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getMovies(page))
//        verify(remote).getMovies(eq(page), any())
//        assertNotNull(movieEntities)
//        assertEquals(movieResponses.size, movieEntities.size)
//    }
//
//    @Test
//    fun getTvShows() {
//        val dataSourceFactory = mock(RemoteDataSource::class.java)
//        `when`(remote.getTvShows()).thenReturn(dataSourceFactory.getTvShows())
//        movieRepository.getTvShows()
//
//        val tvShowEntities = PagedListUtil.mockPagedList(tvShowResponses)
//        verify(remote).getTvShows()
//        assertNotNull(tvShowEntities)
//        assertEquals(tvShowResponses.size, tvShowEntities.size)

//        doAnswer { invocation ->
//            (invocation.arguments[1] as RemoteDataSource.LoadTvShowsCallback)
//                .onAllTvShowsReceived(tvShowResponses)
//            null
//        }.`when`(remote).getTvShows(eq(page), any())
//        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getTvShows(page))
//        verify(remote).getTvShows(eq(page), any())
//        assertNotNull(tvShowEntities)
//        assertEquals(tvShowResponses.size, tvShowEntities.size)
//    }

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

    @Test
    fun getFavMovie(){
        val dummyFavMovie = MutableLiveData<List<MovieEntity>>()
        dummyFavMovie.value = DataDummy.generateDummyMovies(1)
        `when`(local.getFavMovie()).thenReturn(dummyFavMovie as LiveData<List<FavoriteEntity>>)

        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getFavMovie())
        verify(local).getFavMovie()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getFavTvShow(){
        val dummyFavTv = MutableLiveData<List<MovieEntity>>()
        dummyFavTv.value = DataDummy.generateDummyTvShows(1)
        `when`(local.getFavTvShow()).thenReturn(dummyFavTv as LiveData<List<FavoriteEntity>>)

        val tvEntites = LiveDataTestUtil.getValue(movieRepository.getFavTv())
        verify(local).getFavTvShow()
        assertNotNull(tvEntites)
        assertEquals(tvShowResponses.size.toLong(), tvEntites.size.toLong())
    }

    @Test
    fun getDetailFavMovie(){
        val dummyFav = MutableLiveData<FavoriteEntity>()
        dummyFav.value = DataDummy.generateDummyFavMovieDetail(movieId)
        `when`(local.getDetailFavMovie(movieId)).thenReturn(dummyFav as LiveData<FavoriteEntity>)

        val result = LiveDataTestUtil.getValue(movieRepository.getDetailFavMovie(movieId))
        verify(local).getDetailFavMovie(movieId)
        assertNotNull(result)
        assertEquals(movieDetail.title, result.title)
    }

    @Test
    fun getDetailFavTv() {
        val dummyFav = MutableLiveData<FavoriteEntity>()
        dummyFav.value = DataDummy.generateDummyFavTvShowDetail(tvId)
        `when`(local.getDetailFavTv(tvId)).thenReturn(dummyFav as LiveData<FavoriteEntity>)

        val result = LiveDataTestUtil.getValue(movieRepository.getDetailFavTv(tvId))
        verify(local).getDetailFavTv(tvId)
        assertNotNull(result)
        assertEquals(tvShowDetail.title, result.title)
    }
}