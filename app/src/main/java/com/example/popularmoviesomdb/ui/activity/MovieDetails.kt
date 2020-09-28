package com.example.popularmoviesomdb.ui.activity

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmoviesomdb.R
import com.example.popularmoviesomdb.ui.adapter.RatingsAdapter
import com.example.popularmoviesomdb.model.Ratings
import com.example.popularmoviesomdb.model.response.MovieDetailsResponse
import com.example.popularmoviesomdb.viewmodel.MovieDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_details.*
import java.util.*

class MovieDetails : AppCompatActivity() {
    var rAdapter: RatingsAdapter? = null
    var ratingsList: List<Ratings> = ArrayList()
    var movieDetailsViewModel: MovieDetailsViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)
        movieDetailsViewModel = ViewModelProviders.of(this).get(
            MovieDetailsViewModel::class.java
        )
        movieDetailsViewModel?.init()
        rAdapter =
            RatingsAdapter(ratingsList)
        val mLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        rv_reviews.layoutManager = mLayoutManager
        rv_reviews.itemAnimator = DefaultItemAnimator()
        rv_reviews.adapter = rAdapter
        card_view.radius = 30f
        card_view.cardElevation = 10f
        card_view_cast.radius = 30f
        card_view_cast.cardElevation = 10f
        synopsis.setAnimationDuration(750L)
        synopsis.setInterpolator(OvershootInterpolator())
        synopsis.expandInterpolator = OvershootInterpolator()
        synopsis.collapseInterpolator = OvershootInterpolator()
        button_toggle.setOnClickListener {
            button_toggle.text = if (synopsis!!.isExpanded) "Read more" else "Read less"
            synopsis?.toggle()
        }
        makeAPICall(intent.getStringExtra("imdbId"))
    }

    fun makeAPICall(imdbId: String?) {
        movieDetailsViewModel?.getMoviesDetailsRepository(imdbId)?.observe(
            this,
            Observer { movieDetails: MovieDetailsResponse ->
                if (movieDetails.poster != null) {
                    Picasso.with(grid_item_image.context).load(movieDetails.poster)
                        .into(grid_item_image)
                }
                tv_title.text = movieDetails.title
                tv_runtime.text = movieDetails.duration
                rating.text = movieDetails.rating
                releaseDate.text = movieDetails.date
                synopsis?.text = movieDetails.plot
                ratingsList = movieDetails.ratings!!
                tv_director.text = movieDetails.director
                tv_actor.text = movieDetails.actors
                tv_writer.text = movieDetails.writer
                rv_reviews.adapter =
                    RatingsAdapter(
                        ratingsList
                    )
            } as Observer<in MovieDetailsResponse?>
        )
    }
}