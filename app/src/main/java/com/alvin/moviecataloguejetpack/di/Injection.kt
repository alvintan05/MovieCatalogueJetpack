package com.alvin.moviecataloguejetpack.di

import android.content.Context
import com.alvin.moviecataloguejetpack.data.MovieRepository
import com.alvin.moviecataloguejetpack.data.source.local.LocalDataSource
import com.alvin.moviecataloguejetpack.data.source.local.room.MovieDatabase
import com.alvin.moviecataloguejetpack.data.source.remote.RemoteDataSource
import java.util.concurrent.Executors

object Injection {
    fun provideRepository(context: Context): MovieRepository {

        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val executorService = Executors.newSingleThreadExecutor()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, executorService)
    }
}