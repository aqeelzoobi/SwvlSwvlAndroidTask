package com.fluci.alldocumentviewer.swvlandroidtask.extensions

import android.content.Context
import com.google.gson.Gson

import org.json.JSONArray
import org.json.JSONObject

fun String.readFile(context: Context): String {
    val builder = StringBuilder()
    context.assets.open(this.toLowerCase()).bufferedReader().useLines { lines ->
        lines.forEach {
            builder.append(it)
        }
    }
    return builder.toString()
}

fun <T> String.readToObjectList(context: Context, type: Class<T>): MutableList<T> {
    val json = this.readFile(context)
    val jsonObject = JSONObject(json)
    val jsonArray = jsonObject.get("movies") as JSONArray
    //val jsonArray = JSONArray(json)
    val list = mutableListOf<T>()
    for (i in 0 until jsonArray.length()) {
        val objStr = jsonArray.getJSONObject(i).toString()
        val t = objStr.fromJson(type)
        list.add(t)
    }


    return list
}
fun <T> String.fromJson(type: Class<T>): T {
    return  Gson().fromJson(this, type)

}
