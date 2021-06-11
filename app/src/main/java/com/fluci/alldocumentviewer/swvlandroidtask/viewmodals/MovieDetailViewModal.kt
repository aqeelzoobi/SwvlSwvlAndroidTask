package com.fluci.alldocumentviewer.swvlandroidtask.viewmodals

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fluci.alldocumentviewer.swvlandroidtask.datamodals.Movie
import com.fluci.alldocumentviewer.swvlandroidtask.datarepositories.MoviesRepositories
import com.fluci.alldocumentviewer.swvlandroidtask.utils.Constants

class MovieDetailViewModal(val moviesRepositories: MoviesRepositories) :ViewModel() {

        val selectedMovie= MutableLiveData<Movie>()

    fun getSelectedMovie(intent:Intent)
    {
        val movie = intent.getSerializableExtra(Constants.KEY_SELECTED_MOVIE) as Movie
        selectedMovie.postValue(movie)
    }


}