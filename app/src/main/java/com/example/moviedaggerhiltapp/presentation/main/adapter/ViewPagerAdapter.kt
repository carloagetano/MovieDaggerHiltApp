package com.example.moviedaggerhiltapp.presentation.main.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviedaggerhiltapp.presentation.main.views.FavoriteMoviesPageFragment
import com.example.moviedaggerhiltapp.presentation.main.views.MoviesPageFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    companion object {
        private const val NUM_TABS = 2
    }

    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MoviesPageFragment()
            1 -> FavoriteMoviesPageFragment()
            else -> {
                MoviesPageFragment()
            }
        }
    }
}