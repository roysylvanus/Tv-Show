package com.malinikali.movieapp.repository

import com.malinikali.movieapp.api.ApiService
import javax.inject.Inject


class MovieRepository
@Inject constructor(private val apiService: ApiService){

    suspend fun getAllTvShows() = apiService.getAllShows()
}