package com.example.moviedaggerhiltapp.presentation.main.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedaggerhiltapp.R
import com.example.moviedaggerhiltapp.data.utils.Resource
import com.example.moviedaggerhiltapp.databinding.FragmentMoviesPageBinding
import com.example.moviedaggerhiltapp.presentation.main.adapter.MovieAdapter
import com.example.moviedaggerhiltapp.presentation.main.viewmodels.MainActivityViewModel
import kotlinx.coroutines.launch

class MoviesPageFragment : Fragment() {

    private lateinit var binding: FragmentMoviesPageBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movies_page,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]

        loadMovieAdapter()
        subscribe()
        onClickListeners()
    }

    private fun subscribe() {
        viewModel.movies.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    movieAdapter.differ.submitList(response.data?.toList())
                }

                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.getAllMoviesFromDb().collect { movieList ->
                    //movieAdapter.differ.submitList(movieList)
                }
            }
        }
    }

    private fun loadMovieAdapter() {
        movieAdapter = MovieAdapter()

        binding.movieRv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun onClickListeners() {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment()
        movieAdapter.setOnItemClickListener {
            viewModel.setSelectedMovie(it)
            findNavController().navigate(action)
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

}