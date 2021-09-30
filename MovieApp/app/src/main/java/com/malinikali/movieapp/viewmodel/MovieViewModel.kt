package com.malinikali.movieapp.viewmodel

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malinikali.movieapp.models.MovieResponseItem
import com.malinikali.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject
constructor(private val movieRepository: MovieRepository): ViewModel(){

    private val _movieResponse = MutableLiveData<List<MovieResponseItem>>()
            val movieResponse:LiveData<List<MovieResponseItem>> get() = _movieResponse


    init {
        getAllShows()
    }

    private fun getAllShows() = viewModelScope.launch {
        movieRepository.getAllTvShows().let { response ->

            if (response.isSuccessful){
                _movieResponse.postValue(response.body())
                Log.e("VIEWMODEL", "${response.body()}")

            } else{
                Log.d("ViewModel", "ERROR ${response.body()}")
            }
        }
        }
    }