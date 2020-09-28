package com.example.popularmoviesomdb.utils

import com.example.popularmoviesomdb.model.response.MovieDetailsResponse
import com.example.popularmoviesomdb.model.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(".")
    fun getMoviesList(
        @Query("apikey") apiKey: String?,
        @Query("s") s: String?,
        @Query("type") type: String?
    ): Call<MoviesResponse?>?

    @GET(".")
    fun getMovieDetails(
        @Query("apikey") apiKey: String?,
        @Query("i") i: String?
    ): Call<MovieDetailsResponse?>?
}