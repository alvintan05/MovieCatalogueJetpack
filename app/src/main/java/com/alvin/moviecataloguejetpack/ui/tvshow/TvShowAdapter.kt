package com.alvin.moviecataloguejetpack.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alvin.moviecataloguejetpack.data.source.remote.response.TvShow
import com.alvin.moviecataloguejetpack.databinding.ItemMovieBinding
import com.alvin.moviecataloguejetpack.ui.detail.DetailMovieActivity
import com.alvin.moviecataloguejetpack.utils.Url
import com.bumptech.glide.Glide

class TvShowAdapter internal constructor() :
    PagedListAdapter<TvShow, TvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

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
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            with(itemView) {
                binding.tvItemTitle.text = tvShow.name
                binding.tvItemRating.text = tvShow.voteAverage.toString()
                binding.tvItemDesc.text = tvShow.overview

                Glide.with(itemView.context)
                    .load(tvShow.posterPath?.let { Url.getUrlPoster(it) })
                    .into(binding.imgItemPoster)

                binding.ticketView.setOnClickListener {
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