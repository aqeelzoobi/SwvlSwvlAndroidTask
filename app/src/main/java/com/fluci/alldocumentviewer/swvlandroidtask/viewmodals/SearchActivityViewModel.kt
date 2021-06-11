package com.fluci.alldocumentviewer.swvlandroidtask.viewmodals

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fluci.alldocumentviewer.swvlandroidtask.activities.MovieDetailActivity
import com.fluci.alldocumentviewer.swvlandroidtask.activities.SearchActivity
import com.fluci.alldocumentviewer.swvlandroidtask.datamodals.Movie
import com.fluci.alldocumentviewer.swvlandroidtask.datarepositories.MoviesRepositories
import com.fluci.alldocumentviewer.swvlandroidtask.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchActivityViewModel(val moviesRepositories: MoviesRepositories):ViewModel() {

    val searchList = MutableLiveData<MutableList<Any>>()

    suspend fun searchAndSetMovies(query:String) {
        val movies=searchMovies(query)
        Log.e("Movies",movies.size.toString())
        updateList(movies)
    }
    fun updateList(movies:MutableList<Any>)
    {
        Log.e("updatingData",movies.size.toString())
        searchList.postValue(movies)
    }
    suspend fun searchMovies(query:String)= withContext(Dispatchers.IO){
        val categorizedmovieslist =moviesRepositories.searchMoviesByTitle(query)
        return@withContext moviesRepositories.getNormalizedAndSortedList(categorizedmovieslist)
    }
    fun navigateToMovieDetail(context:AppCompatActivity,movie:Movie)
    {
        context.startActivity(Intent(context, MovieDetailActivity::class.java).putExtra(Constants.KEY_SELECTED_MOVIE,movie))
    }

}