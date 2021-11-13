package com.example.lists.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lists.R
import com.example.lists.Tour
import java.text.FieldPosition

class RecyclerViewAdapter(private var objects: List<Tour>,
private val clickListener: (Tour) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclingViewHolder =
        RecyclingViewHolder(LayoutInflater.from(parent.context.applicationContext)
            .inflate(R.layout.item_list_view, parent, false)){
            clickListener(objects[it])
        }

     //return   RecyclingViewHolder(LayoutInflater.from(parent.context.applicationContext).inflate(R.layout.item_list_view, parent, false))


    override fun onBindViewHolder(holder: RecyclingViewHolder, position: Int) {
        holder.bind(objects[position])
    }

    override fun getItemCount(): Int = objects.size

    fun updateList(element: Tour){
        val mutableObjects =  objects.toMutableList()
        mutableObjects[2] = element
        objects = mutableObjects
    }
    fun removeListElement(): List<Tour> {
        val mutableObjects =  objects.toMutableList()
        mutableObjects.removeAt(2)
        objects = mutableObjects.also{
            objects = it
        }
        return objects
    }

    class RecyclingViewHolder(itemView: View,
    clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Tour) {
            itemView.findViewById<TextView>(R.id.item_title).text = item.title//title?
        }
        init{
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
    }
}