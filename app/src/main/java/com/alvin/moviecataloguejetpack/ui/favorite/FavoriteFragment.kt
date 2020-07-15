package com.alvin.moviecataloguejetpack.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alvin.moviecataloguejetpack.R
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
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
            view_pager_home.adapter = sectionsPagerAdapter
            tabs_home.setupWithViewPager(view_pager_home)
        }
    }

}
