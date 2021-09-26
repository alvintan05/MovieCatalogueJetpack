package com.alvin.moviecataloguejetpack.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvin.moviecataloguejetpack.databinding.FragmentFavoriteTvShowBinding
import com.alvin.moviecataloguejetpack.ui.favorite.FavoriteMovieAdapter
import com.alvin.moviecataloguejetpack.viewmodel.ViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvShowFragment : Fragment() {

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding.root
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
            binding.progressBar.visibility = View.VISIBLE

            viewModel.data.observe(viewLifecycleOwner, Observer { movies ->
                binding.progressBar.visibility = View.GONE
                adapter.setMovies(movies, 2)
                adapter.notifyDataSetChanged()
            })

            with(binding.rvFavTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
