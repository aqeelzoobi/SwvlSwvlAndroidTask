package com.fluci.alldocumentviewer.swvlandroidtask.listeners

import com.fluci.alldocumentviewer.swvlandroidtask.datamodals.Movie

interface MovieItemClick {
    fun onMovieClick(position:Int,movie:Movie)
}