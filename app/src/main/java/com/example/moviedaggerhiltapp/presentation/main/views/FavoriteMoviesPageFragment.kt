package com.example.moviedaggerhiltapp.presentation.main.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.moviedaggerhiltapp.R
import com.example.moviedaggerhiltapp.databinding.FragmentFavoriteMoviesPageBinding

class FavoriteMoviesPageFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMoviesPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorite_movies_page,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}