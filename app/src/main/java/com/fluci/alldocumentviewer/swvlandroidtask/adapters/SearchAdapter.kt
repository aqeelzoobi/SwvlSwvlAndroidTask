package com.fluci.alldocumentviewer.swvlandroidtask.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fluci.alldocumentviewer.swvlandroidtask.R
import com.fluci.alldocumentviewer.swvlandroidtask.datamodals.Movie
import com.fluci.alldocumentviewer.swvlandroidtask.datamodals.YearHeader
import com.fluci.alldocumentviewer.swvlandroidtask.listeners.MovieItemClick
import kotlinx.android.synthetic.main.content_search_movie_item.view.*
import kotlinx.android.synthetic.main.content_search_year_item.view.*

class SearchAdapter(private var searchItems : ArrayList<Any>, private val activity: Activity,private val movieClickHandler:MovieItemClick) :    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        when(viewType)
        {
            ITEM_MOVIE ->
            {

                val v = LayoutInflater.from(parent.context)
                        .inflate(R.layout.content_search_movie_item,parent,false)
                return ViewHolder(v, viewType)
            }
            ITEM_YEAR_HEADER ->
            {

                val v = LayoutInflater.from(parent.context)
                        .inflate(R.layout.content_search_year_item,parent,false)
                return ViewHolder(v,viewType)
            }

        }
        return null!!
    }

    var updatedSearchList
        get() = searchItems
        set(value) {
            searchItems.clear()
            searchItems.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return searchItems.size
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        when(getItemViewType(position))
        {
            ITEM_MOVIE ->
            {   holder as ViewHolder
                val movie_item = searchItems[position] as Movie
                holder.movieName.setText(movie_item.title)
                holder.movieRating.setText(movie_item.rating.toString())
                holder.itemView.setOnClickListener {
                    movieClickHandler.onMovieClick(position,movie_item)
                }
            }
            ITEM_YEAR_HEADER ->{
                holder as ViewHolder
                val yearItem = searchItems[position] as YearHeader
                holder.yearName.setText(yearItem.yearName)
            }

        }


    }

    override fun getItemViewType(position: Int): Int {

        if(searchItems[position] is Movie)
            return ITEM_MOVIE
        else if(searchItems.get(position) is YearHeader) return ITEM_YEAR_HEADER
        else { return 0}
    }
    inner class ViewHolder(itemView : View,viewType: Int) : RecyclerView.ViewHolder(itemView){
        lateinit var movieName:TextView
        lateinit var movieRating:TextView
        lateinit var movieImg:ImageView
        lateinit var yearName: TextView
        init {

            when(viewType)
            {
                ITEM_MOVIE ->{
                     movieName =itemView.movie_title
                     movieRating = itemView.txt_rating_count
                     movieImg = itemView.img_movie
                }
                ITEM_YEAR_HEADER ->{
                    yearName=itemView.txt_year
                }
            }
        }


    }

    companion object{
        val ITEM_MOVIE=1
        val ITEM_YEAR_HEADER=2

    }
}
