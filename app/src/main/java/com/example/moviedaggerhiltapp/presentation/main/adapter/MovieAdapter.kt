package com.example.moviedaggerhiltapp.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedaggerhiltapp.R
import com.example.moviedaggerhiltapp.data.models.MovieResponse
import com.example.moviedaggerhiltapp.databinding.ItemMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<MovieResponse>() {
        override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie, parent, false
        )

        return MovieViewHolder(itemMovieBinding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = differ.currentList[position]

        holder.itemMovieBinding.apply {
            movie = currentMovie

            Glide.with(this.root)
                .load(currentMovie.images[0])
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .into(moviePosterImg)

            root.setOnClickListener {
                onItemClickListener?.let { it(currentMovie) }
            }
        }
    }

    private var onItemClickListener: ((MovieResponse) -> Unit)? = null

    fun setOnItemClickListener(listener: (MovieResponse) -> Unit) {
        onItemClickListener = listener
    }

    class MovieViewHolder(val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root)
}