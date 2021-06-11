package com.fluci.alldocumentviewer.swvlandroidtask.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fluci.alldocumentviewer.swvlandroidtask.R
import com.fluci.alldocumentviewer.swvlandroidtask.adapters.MoviesAdapter
import com.fluci.alldocumentviewer.swvlandroidtask.datarepositories.MoviesRepositories
import com.fluci.alldocumentviewer.swvlandroidtask.viewmodals.MainActivityViewModal
import kotlinx.android.synthetic.main.content_main_movies_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm: MainActivityViewModal by viewModel()
    lateinit var moviesList:ArrayList<Any>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main_movies_view)
        setListeners()
        rv_movies.layoutManager = GridLayoutManager(this,2,RecyclerView.VERTICAL,false)
        rv_movies.adapter = adapter
        vm.movieslist.observe(this, Observer {
            Log.e("MainActivity","Observer Updated")
            moviesList= ArrayList()
            moviesList.addAll(it.toMutableList())
            adapter.items=moviesList
        })
       loadMovies()



    }
    fun setListeners()
    {
        tv_search.setOnClickListener {
            vm.navigateToSearchScreen()
        }

    }
    fun loadMovies()
    {
        GlobalScope.launch(Dispatchers.Main) {
            vm.getAndSetMoviesList()
        }
//        val moviesRepositories=MoviesRepositories(this)
//        val sortedList =moviesRepositories.loadAllMovies(this)
    }
    val adapter: MoviesAdapter by lazy{
        MoviesAdapter(
            ArrayList(),
            this
            )
    }
}