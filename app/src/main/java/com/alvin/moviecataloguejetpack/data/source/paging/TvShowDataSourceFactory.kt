package com.alvin.moviecataloguejetpack.data.source.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow

class TvShowDataSourceFactory: DataSource.Factory<Int, TvShow>() {

    private val tvLiveDataSource = MutableLiveData<TvShowDataSource>()

    override fun create(): DataSource<Int, TvShow> {
        val tvDataSource = TvShowDataSource()
        tvLiveDataSource.postValue(tvDataSource)
        return tvDataSource
    }
}