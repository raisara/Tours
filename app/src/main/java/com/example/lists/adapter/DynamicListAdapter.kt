package com.example.lists.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.lists.R
import com.example.lists.Tour
import com.example.lists.TourDiffutilItemCallback

class DynamicListAdapter(private val clickListener: (Tour) -> Unit)
    : ListAdapter<Tour, DynamicListAdapter.RecyclingViewHolder>(
    //TourDiffutilItemCallback()
    AsyncDifferConfig.Builder(TourDiffutilItemCallback()).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclingViewHolder =
        RecyclingViewHolder(
            LayoutInflater.from(parent.context.applicationContext)
                .inflate(R.layout.item_list_view, parent, false)
        ){
            clickListener(getItem(it))

        }

    override fun onBindViewHolder(holder: RecyclingViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { clickListener(getItem(position)) }
    }

    class RecyclingViewHolder(itemView: View, clickListener: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Tour) {
            itemView.findViewById<TextView>(R.id.item_title).text = item.title
            itemView.findViewById<TextView>(R.id.item_price).text = item.price.toString()
            /*itemView.findViewById<ImageView>(R.id.item_image).load(item.img){
                transformations(CircleCropTransformation())
            }*/

        }
        init{
            itemView.setOnClickListener {
                clickListener(adapterPosition)
            }
        }

    }
}