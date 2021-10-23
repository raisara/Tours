package com.example.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.lists.adapter.ListViewAdapter

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val listView = view.findViewById<ListView>(R.id.listView)
       // val objects = listOf("1", "2", "3")

        listView.adapter = ListViewAdapter(
            context = context ?: return,
            objects = listOf("1", "2", "3", "4", "5")
        )
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}