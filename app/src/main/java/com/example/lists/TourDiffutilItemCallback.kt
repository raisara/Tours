package com.example.lists

import androidx.recyclerview.widget.DiffUtil

class TourDiffutilItemCallback() : DiffUtil.ItemCallback<Tour>() {
    override fun areItemsTheSame(oldItem: Tour, newItem: Tour): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Tour, newItem: Tour): Boolean = oldItem == newItem

}