package com.alvin.moviecataloguejetpack.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.ui.detail.DetailMovieActivity
import com.alvin.moviecataloguejetpack.utils.Url
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val listMovies = ArrayList<MovieEntity>()
    private var movieType: Int = 0

    fun setMovies(movies: List<MovieEntity>?, type: Int) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
        movieType = type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity) {
            with(itemView) {
                tv_item_title.text = movie.title
                tv_item_rating.text = movie.rating.toString()
                tv_item_desc.text = movie.overview

                Glide.with(itemView.context)
                    .load(Url.getUrlPoster(movie.posterPath))
                    .into(img_item_poster)

                ticket_view.setOnClickListener {
                    val intent = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.EXTRA_ID, movie.movieId)
                        putExtra(DetailMovieActivity.EXTRA_TYPE, movieType)
                    }
                    context.startActivity(intent)
                }
            }
        }

    }
}