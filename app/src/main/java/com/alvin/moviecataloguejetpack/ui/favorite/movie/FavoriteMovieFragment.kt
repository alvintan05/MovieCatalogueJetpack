package com.alvin.moviecataloguejetpack.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.ui.favorite.FavoriteMovieAdapter
import com.alvin.moviecataloguejetpack.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_movie.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this,
                factory
            )[FavoriteMovieViewModel::class.java]

            val adapter = FavoriteMovieAdapter()
            progress_bar.visibility = View.VISIBLE

            viewModel.data.observe(viewLifecycleOwner, Observer { movies ->
                progress_bar.visibility = View.GONE
                adapter.setMovies(movies, 1)
                adapter.notifyDataSetChanged()
            })

            with(rv_fav_movies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

}