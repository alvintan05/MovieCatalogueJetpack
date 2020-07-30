package com.alvin.moviecataloguejetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.alvin.moviecataloguejetpack.data.source.local.DetailMovieEntity
import com.alvin.moviecataloguejetpack.data.source.local.LocalDataSource
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity
import com.alvin.moviecataloguejetpack.data.source.remote.RemoteDataSource
import com.alvin.moviecataloguejetpack.data.source.remote.response.DetailMovieResponse
import com.alvin.moviecataloguejetpack.data.source.remote.response.DetailTvShowResponse
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow
import java.util.concurrent.ExecutorService

class FakeMovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val executorService: ExecutorService
) :
    MovieDataSource {
    
    override fun getMovies(): LiveData<PagedList<Movie>> {
        return remoteDataSource.getMovies()
    }

    override fun getTvShows(): LiveData<PagedList<TvShow>> {
        return remoteDataSource.getTvShows()
    }

    override fun getDetailMovie(movieId: Int): LiveData<DetailMovieEntity> {
        val detailResult = MutableLiveData<DetailMovieEntity>()

        remoteDataSource.getDetailMovie(movieId,
            object : RemoteDataSource.LoadDetailMovieCallback {
                override fun onDetailMovieReceived(detailMovie: DetailMovieResponse) {
                    val movie = DetailMovieEntity(
                        detailMovie.id,
                        detailMovie.title,
                        detailMovie.releaseDate,
                        detailMovie.runtime,
                        detailMovie.rating,
                        detailMovie.genres?.joinToString(", ", "", ".", 3, "etc") {
                            it.name.toString()
                        },
                        detailMovie.overview,
                        detailMovie.posterPath,
                        detailMovie.backdropPath
                    )

                    detailResult.postValue(movie)
                }

            })

        return detailResult
    }

    override fun getDetailTvShow(tvId: Int): LiveData<DetailMovieEntity> {
        val detailResult = MutableLiveData<DetailMovieEntity>()

        remoteDataSource.getDetailTvShow(tvId,
            object : RemoteDataSource.LoadDetailTvShowCallback {
                override fun onDetailTvShowReceived(detailTvShow: DetailTvShowResponse) {
                    val tvShow = DetailMovieEntity(
                        detailTvShow.id,
                        detailTvShow.title,
                        detailTvShow.releaseDate,
                        detailTvShow.episodeRunTime?.average()?.toInt(),
                        detailTvShow.rating,
                        detailTvShow.genres?.joinToString(", ", "", ".", 3, "etc") {
                            it.name.toString()
                        },
                        detailTvShow.overview,
                        detailTvShow.posterPath,
                        detailTvShow.backdropPath
                    )

                    detailResult.postValue(tvShow)
                }

            })

        return detailResult
    }

    override fun getFavMovie(): LiveData<List<FavoriteEntity>> = localDataSource.getFavMovie()

    override fun getFavTv(): LiveData<List<FavoriteEntity>> = localDataSource.getFavTvShow()

    override fun getDetailFavMovie(id: Int): LiveData<FavoriteEntity> =
        localDataSource.getDetailFavMovie(id)

    override fun getDetailFavTv(id: Int): LiveData<FavoriteEntity> =
        localDataSource.getDetailFavTv(id)

    override fun getIsFavorite(id: Int, type: Int): LiveData<Boolean> =
        localDataSource.getIsFavorite(id, type)

    override fun addFavorite(favoriteEntity: FavoriteEntity) {
        executorService.execute { localDataSource.addFavorite(favoriteEntity) }
    }

    override fun deleteFavorite(id: Int, type: Int) {
        executorService.execute { localDataSource.deleteFavorite(id, type) }
    }
}