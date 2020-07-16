package com.alvin.moviecataloguejetpack.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.ui.favorite.FavoriteMovieAdapter
import com.alvin.moviecataloguejetpack.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this,
                factory
            )[FavoriteTvShowViewModel::class.java]

            val adapter = FavoriteMovieAdapter()
            progress_bar.visibility = View.VISIBLE

            viewModel.data.observe(viewLifecycleOwner, Observer { movies ->
                progress_bar.visibility = View.GONE
                adapter.setMovies(movies, 2)
                adapter.notifyDataSetChanged()
            })

            with(rv_fav_tv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

}
