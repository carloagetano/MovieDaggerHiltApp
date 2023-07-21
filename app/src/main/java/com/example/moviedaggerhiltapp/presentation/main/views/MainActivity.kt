package com.example.moviedaggerhiltapp.presentation.main.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedaggerhiltapp.R
import com.example.moviedaggerhiltapp.data.utils.Resource
import com.example.moviedaggerhiltapp.databinding.ActivityMainBinding
import com.example.moviedaggerhiltapp.presentation.main.adapter.MovieAdapter
import com.example.moviedaggerhiltapp.presentation.main.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding.lifecycleOwner = this

        loadMovieAdapter()
        subscribe()
        onClickListeners()
    }

    private fun subscribe() {
        viewModel.movies.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    movieAdapter.differ.submitList(response.data?.toList())
                }

                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun loadMovieAdapter() {
        movieAdapter = MovieAdapter()

        binding.movieRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun onClickListeners() {
        movieAdapter.setOnItemClickListener {

        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}