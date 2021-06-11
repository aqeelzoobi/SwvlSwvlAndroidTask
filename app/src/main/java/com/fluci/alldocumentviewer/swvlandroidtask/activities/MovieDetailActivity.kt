package com.fluci.alldocumentviewer.swvlandroidtask.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.fluci.alldocumentviewer.swvlandroidtask.R
import com.fluci.alldocumentviewer.swvlandroidtask.datamodals.Movie
import com.fluci.alldocumentviewer.swvlandroidtask.viewmodals.MovieDetailViewModal
import com.fluci.alldocumentviewer.swvlandroidtask.viewmodals.SearchActivityViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {
    private val movieDetailViewModal: MovieDetailViewModal by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        movieDetailViewModal.getSelectedMovie(intent)
        movieDetailViewModal.selectedMovie.observe(this, Observer{

            setMovieDetails(it)
        })
    }
    fun setMovieDetails(movie:Movie)
    {
        movie_title.setText(movie.title)
        txt_genres.setText(setMovieGenres(movie.genres))
        txt_rating.setText(movie.rating)
        txt_movie_year.setText(movie.year.toString())

    }
    fun setMovieGenres(genres:ArrayList<String>):String
    {
        val genresText = StringBuilder()

        for(genre in genres)
        {
            genresText.append(genre).append(",")
        }
        return genresText.deleteCharAt(genresText.length - 1).toString()
    }
}