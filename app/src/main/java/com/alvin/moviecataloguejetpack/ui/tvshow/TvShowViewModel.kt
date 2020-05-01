package com.alvin.moviecataloguejetpack.ui.tvshow

import androidx.lifecycle.ViewModel
import com.alvin.moviecataloguejetpack.data.MovieEntity
import com.alvin.moviecataloguejetpack.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShows(): List<MovieEntity> = DataDummy.generateDummyTvShows()
}