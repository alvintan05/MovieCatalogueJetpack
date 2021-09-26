package com.alvin.moviecataloguejetpack.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.databinding.FragmentFavoriteBinding
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            // set up tab and view pager
            val sectionsPagerAdapter =
                context?.let {
                    SectionsPagerAdapter(
                        it,
                        childFragmentManager
                    )
                }
            binding.viewPagerHome.adapter = sectionsPagerAdapter
            binding.tabsHome.setupWithViewPager(view_pager_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
