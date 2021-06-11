package com.fluci.alldocumentviewer.swvlandroidtask.viewmodals

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fluci.alldocumentviewer.swvlandroidtask.activities.SearchActivity
import com.fluci.alldocumentviewer.swvlandroidtask.datamodals.Movie
import com.fluci.alldocumentviewer.swvlandroidtask.datarepositories.MoviesRepositories
import kotlinx.coroutines.*

class MainActivityViewModal(private val moviesRepositories: MoviesRepositories,val context: Context):ViewModel() {

    val movieslist = MutableLiveData<MutableList<Movie>>()
    suspend fun getAndSetMoviesList() {
       val movies=getMovies()
        Log.e("Movies",movies.size.toString())
       updateList(movies as ArrayList<Movie>)
    }
    fun updateList(movies:MutableList<Movie>)
    {
        Log.e("updatingData",movies.size.toString())
        movieslist.postValue(movies)
    }
    suspend fun getMovies()= withContext(Dispatchers.IO){
        moviesRepositories.loadAllMovies(context)
    }

    fun navigateToSearchScreen()
    {
        context.startActivity(Intent(context, SearchActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }


}