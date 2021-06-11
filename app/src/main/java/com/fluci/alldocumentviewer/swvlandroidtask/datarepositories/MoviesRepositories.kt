package com.fluci.alldocumentviewer.swvlandroidtask.datarepositories

import android.content.Context
import com.fluci.alldocumentviewer.swvlandroidtask.datamodals.Movie
import com.fluci.alldocumentviewer.swvlandroidtask.datamodals.YearHeader
import com.fluci.alldocumentviewer.swvlandroidtask.extensions.readToObjectList

class MoviesRepositories(val context: Context) {

    lateinit var movieslist:ArrayList<Movie>
    fun loadAllMovies(context:Context):ArrayList<Any>
    {
        movieslist="movies.json".readToObjectList(context,Movie::class.java) as ArrayList<Movie>
        val sortedlistbyYear =movieslist.sortedByDescending { it.year }
        movieslist.clear()
        movieslist.addAll(sortedlistbyYear)
        return movieslist as ArrayList<Any>

    }
    fun searchMoviesByTitle(query:String):ArrayList<ArrayList<Movie>>
    {
        val categorizedYearList=ArrayList<ArrayList<Movie>>()
        var newYearList=ArrayList<Movie>()
        var search_year=movieslist.get(0).year
        for((index,movie) in movieslist.withIndex())
        {
            /* first we will check that, search name must be exsit in list*/
            if(movie.title.startsWith(query,true))
            {
                /* here we check that the year, last process data year is same as current item year or not*/
                if(search_year!=movie.year)
                {
                    newYearList.add(movie)
                    if(newYearList.size!=0) categorizedYearList.add(newYearList)
                    search_year=movie.year

                    newYearList= ArrayList()
                    // create new arraylist
                }else
                {
                    newYearList.add(movie)
                }


                if(index==movieslist.size-1)
                {
                    if(newYearList.size!=0)
                    categorizedYearList.add(newYearList)
                }
            }
        }
        return categorizedYearList
    }
    fun getNormalizedAndSortedList(categorizedYearList:ArrayList<ArrayList<Movie>>):ArrayList<Any>
    {
        val sortedWithYearRating=ArrayList<Any>()
        for(moviesWithYear in categorizedYearList)
        {
            var sortedList =  moviesWithYear.sortedWith(compareBy({it.rating})).sortedByDescending { it.rating }
            sortedWithYearRating.add(YearHeader(moviesWithYear.get(0).year.toString()))
            sortedWithYearRating.addAll(sortedList)

        }
        return sortedWithYearRating
    }
}