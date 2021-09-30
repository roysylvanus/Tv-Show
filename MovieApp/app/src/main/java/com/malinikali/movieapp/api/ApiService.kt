package com.malinikali.movieapp.api

import com.malinikali.movieapp.models.MovieResponse
import com.malinikali.movieapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.SHOWS)
    suspend fun getAllShows(): Response<MovieResponse>
}