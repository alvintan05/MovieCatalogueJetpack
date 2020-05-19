package com.alvin.moviecataloguejetpack.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvin.moviecataloguejetpack.BuildConfig
import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.data.source.remote.network.RetrofitServer
import com.alvin.moviecataloguejetpack.ui.movie.MovieAdapter
import com.alvin.moviecataloguejetpack.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(
                this,
                factory
            )[TvShowViewModel::class.java]

            val adapter = MovieAdapter()

            progress_bar.visibility = View.VISIBLE
            viewModel.getTvShows(BuildConfig.TMDB_API_KEY, 1).observe(this, Observer { shows ->
                progress_bar.visibility = View.GONE
                adapter.setMovies(shows, 2)
                adapter.notifyDataSetChanged()
            })

            with(rv_tv_shows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }

        }
    }

}
