package com.fluci.alldocumentviewer.swvlandroidtask.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fluci.alldocumentviewer.swvlandroidtask.R
import com.fluci.alldocumentviewer.swvlandroidtask.datamodals.Movie
import kotlinx.android.synthetic.main.content_movie_item.view.*


class MoviesAdapter(private var movieitems : ArrayList<Any>, private val activity: Activity) :    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        when(viewType)
        {
            ITEM_MOVIE ->
            {

                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.content_movie_item,parent,false)
                return ViewHolder(v)
            }

        }
        return null!!
    }

    var items
        get() = movieitems
        set(value) {
            movieitems.clear()
            movieitems.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return movieitems.size
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        when(getItemViewType(position))
        {
            ITEM_MOVIE ->
            {   holder as ViewHolder
                val movie_item = movieitems[position] as Movie
                holder.movie_name.setText(movie_item.title)
                holder.movie_rating.setText(movie_item.rating)
                holder.movie_Img.setOnClickListener {

                }
            }

        }


    }

    override fun getItemViewType(position: Int): Int {

        if(movieitems[position] is Movie)
            return ITEM_MOVIE
        else { return 0}
    }
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val movie_name: TextView =itemView.movie_title
        val movie_rating:TextView = itemView.txt_rating
        val movie_Img:ImageView = itemView.img_movie

    }

    companion object{
        val ITEM_MOVIE=1

    }
}
