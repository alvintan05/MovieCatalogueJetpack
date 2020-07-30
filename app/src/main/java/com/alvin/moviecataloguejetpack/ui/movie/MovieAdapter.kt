package com.alvin.moviecataloguejetpack.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.data.source.local.MovieEntity
import com.alvin.moviecataloguejetpack.data.source.remote.response.Movie
import com.alvin.moviecataloguejetpack.ui.detail.DetailMovieActivity
import com.alvin.moviecataloguejetpack.utils.Url
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter internal constructor() : PagedListAdapter<Movie, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                tv_item_title.text = movie.title
                tv_item_rating.text = movie.voteAverage.toString()
                tv_item_desc.text = movie.overview

                Glide.with(itemView.context)
                    .load(movie.posterPath?.let { Url.getUrlPoster(it) })
                    .into(img_item_poster)

                ticket_view.setOnClickListener {
                    val intent = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.EXTRA_ID, movie.id)
                        putExtra(DetailMovieActivity.EXTRA_TYPE, 1)
                    }
                    context.startActivity(intent)
                }
            }
        }

    }
}