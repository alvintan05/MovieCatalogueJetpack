package com.alvin.moviecataloguejetpack.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.data.source.local.entity.FavoriteEntity
import com.alvin.moviecataloguejetpack.databinding.ItemMovieBinding
import com.alvin.moviecataloguejetpack.ui.detail.DetailMovieActivity
import com.alvin.moviecataloguejetpack.utils.Url
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    private val listMovies = ArrayList<FavoriteEntity>()
    private var movieType: Int = 0

    fun setMovies(movies: List<FavoriteEntity>?, type: Int) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
        movieType = type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: FavoriteEntity) {
            with(itemView) {
                binding.tvItemTitle.text = movie.title
                binding.tvItemRating.text = movie.rating.toString()
                binding.tvItemDesc.text = movie.overview

                Glide.with(itemView.context)
                    .load(movie.posterPath?.let { Url.getUrlPoster(it) })
                    .into(binding.imgItemPoster)

                binding.ticketView.setOnClickListener {
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