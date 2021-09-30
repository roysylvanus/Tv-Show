package com.malinikali.movieapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.malinikali.movieapp.databinding.MovieItemBinding
import com.malinikali.movieapp.models.MovieResponseItem
import java.net.URI

class MovieAdapter:RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    private val diffUtilCallback = object : DiffUtil.ItemCallback<MovieResponseItem>(){
        override fun areItemsTheSame(
            oldItem: MovieResponseItem,
            newItem: MovieResponseItem
        ): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: MovieResponseItem,
            newItem: MovieResponseItem
        ): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this,diffUtilCallback)

    var movieList : List<MovieResponseItem>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    inner class MovieViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false) )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = movieList[position]

        holder.binding.apply {
            textView.text = currentMovie.name
            imageView.load(currentMovie.image.original){
                crossfade(true)
                crossfade(1000)
            }

        }
        holder.itemView.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(currentMovie.officialSite)
           it.context.startActivity(openUrl)
        }


    }

    override fun getItemCount() = movieList.size
}