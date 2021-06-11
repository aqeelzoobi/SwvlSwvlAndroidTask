package com.fluci.alldocumentviewer.swvlandroidtask.datamodals

import java.io.Serializable

data  class Movie(val title:String,val year:Int,
                  val cast:ArrayList<String>,val genres:ArrayList<String>,val rating:String):Serializable