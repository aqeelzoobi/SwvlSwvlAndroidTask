package com.fluci.alldocumentviewer.swvlandroidtask.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fluci.alldocumentviewer.swvlandroidtask.R
import com.fluci.alldocumentviewer.swvlandroidtask.adapters.SearchAdapter
import com.fluci.alldocumentviewer.swvlandroidtask.datamodals.Movie
import com.fluci.alldocumentviewer.swvlandroidtask.listeners.MovieItemClick
import com.fluci.alldocumentviewer.swvlandroidtask.viewmodals.SearchActivityViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private val searchViewModal: SearchActivityViewModel by viewModel()
    lateinit var searchDataList:ArrayList<Any>

    val searchAdapter: SearchAdapter by lazy{
        SearchAdapter(
                ArrayList(),
                this,
                object :MovieItemClick{
                    override fun onMovieClick(position: Int, movie: Movie) {
                        searchViewModal.navigateToMovieDetail(this@SearchActivity,movie)
                    }

                }
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setListeners()
        rv_search_result.layoutManager = LinearLayoutManager(this)
        rv_search_result.adapter = searchAdapter
        searchViewModal.searchList.observe(this, Observer {
            Log.e("MainActivity","Observer Updated")
            searchDataList= ArrayList()
            searchDataList.addAll(it.toMutableList())
            Log.e("SearchResult","${searchDataList.size}")
            searchAdapter.updatedSearchList=searchDataList
        })


    }
    fun setListeners()
    {
        iv_search.setOnClickListener {
            val query = edtx_search.text.toString()
            if(query.isNotEmpty())
            {
                searchMovies(query)
            }

        }
    }

    fun searchMovies(query:String)
    {
        GlobalScope.launch(Dispatchers.Main) {
            searchViewModal.searchAndSetMovies(query)
        }
    }
}