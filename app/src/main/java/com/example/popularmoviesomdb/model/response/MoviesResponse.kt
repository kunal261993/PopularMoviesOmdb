package com.example.popularmoviesomdb.model.response

import com.example.popularmoviesomdb.model.Movie
import com.google.gson.annotations.SerializedName

class MoviesResponse {
    @SerializedName("Search")
    var results: List<Movie>? = null

}