package com.example.popularmoviesomdb.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.popularmoviesomdb.ui.adapter.MoviesAdapter.MovieViewHolder
import com.example.popularmoviesomdb.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item_layout.view.*

class MoviesAdapter(
    private val movies: List<Movie>,
    private val rowLayout: Int,
    private val context: Context
) : RecyclerView.Adapter<MovieViewHolder>() {

    class MovieViewHolder(v: View) : ViewHolder(v) {
        var movieTitle = v.detail_title
        var year = v.release_year
        var imageView= v.grid_item_image
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieTitle.text = movies[position].title
        holder.year.text = movies[position].year
        Picasso.with(context).load(movies[position].poster).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}