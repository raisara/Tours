package com.example.lists.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.lists.R
import com.example.lists.Tour

class ListViewAdapter(context: Context, private val objects: List<Tour>) :
    ArrayAdapter<Tour>(context, R.layout.item_list_view, R.id.item_title, objects) {

    override fun getCount(): Int = objects.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_list_view, parent, false)
        view?.findViewById<TextView>(R.id.item_title)?.text = objects[position].toString()
        return view
    }
}