package com.alvin.moviecataloguejetpack.data.source

import com.alvin.moviecataloguejetpack.data.source.remote.RemoteDataSource
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class MovieRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)
    private val page = 1



    @Test
    fun getMovies() {
        `when`(remote.getMovies(page)).thenReturn()
        val movieEntities = movieRepository.getMovies(page)
        verify()
    }

    @Test
    fun getTvShows() {
    }

    @Test
    fun getDetailMovie() {
    }

    @Test
    fun getDetailTvShow() {
    }
}