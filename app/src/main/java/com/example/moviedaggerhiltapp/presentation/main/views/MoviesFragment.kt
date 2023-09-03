package com.example.moviedaggerhiltapp.presentation.main.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviedaggerhiltapp.R
import com.example.moviedaggerhiltapp.databinding.FragmentMoviesBinding
import com.example.moviedaggerhiltapp.presentation.main.adapter.ViewPagerAdapter
import com.example.moviedaggerhiltapp.presentation.main.viewmodels.MainActivityViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movies, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]

        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.title_pages)[position]
        }.attach()
    }
}