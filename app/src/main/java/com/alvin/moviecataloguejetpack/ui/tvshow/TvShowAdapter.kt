package com.alvin.moviecataloguejetpack.ui.tvshow

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
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow
import com.alvin.moviecataloguejetpack.ui.detail.DetailMovieActivity
import com.alvin.moviecataloguejetpack.utils.Url
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class TvShowAdapter internal constructor() : PagedListAdapter<TvShow, TvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShow) {
            with(itemView) {
                tv_item_title.text = tvShow.name
                tv_item_rating.text = tvShow.voteAverage.toString()
                tv_item_desc.text = tvShow.overview

                Glide.with(itemView.context)
                    .load(tvShow.posterPath?.let { Url.getUrlPoster(it) })
                    .into(img_item_poster)

                ticket_view.setOnClickListener {
                    val intent = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.EXTRA_ID, tvShow.id)
                        putExtra(DetailMovieActivity.EXTRA_TYPE, 2)
                    }
                    context.startActivity(intent)
                }
            }
        }

    }
}