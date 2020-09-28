package com.example.popularmoviesomdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.popularmoviesomdb.model.response.MovieDetailsResponse
import com.example.popularmoviesomdb.repository.MoviesRepository

class MovieDetailsViewModel : ViewModel() {
    private var movieDetailsLiveData: MutableLiveData<MovieDetailsResponse?>? = null
    private var moviesRepository: MoviesRepository? = null
    fun init() {
        if (movieDetailsLiveData != null) {
            return
        }
        moviesRepository =
            MoviesRepository.instance
    }

    fun getMoviesDetailsRepository(imdbId: String?): LiveData<MovieDetailsResponse?> {
        movieDetailsLiveData = moviesRepository?.getMovieDetails(imdbId)
        return movieDetailsLiveData!!
    }
}