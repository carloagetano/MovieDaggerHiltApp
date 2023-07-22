package com.example.moviedaggerhiltapp.presentation.main.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.moviedaggerhiltapp.R
import com.example.moviedaggerhiltapp.data.models.MovieResponse
import com.example.moviedaggerhiltapp.databinding.FragmentMovieDetailsBinding
import com.example.moviedaggerhiltapp.presentation.main.viewmodels.MainActivityViewModel

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_details, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]

        viewModel.selectedMovie.value?.let { setMovieData(it) }

        onClickListeners()
    }

    private fun setMovieData(movie: MovieResponse) {
        binding.movie = movie

        //Set movie poster image
        Glide.with(this)
            .load(movie.images[0])
            .centerCrop()
            .into(binding.moviePosterImg)

        //Set movie BG image
        Glide.with(this)
            .load(movie.images[1])
            .centerCrop()
            .into(binding.movieBgPosterImg)
    }

    private fun onClickListeners() {
        binding.movieDetailTitleTv.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}