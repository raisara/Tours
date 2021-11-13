package com.example.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import coil.load
import com.example.lists.adapter.DynamicListAdapter
import com.example.lists.adapter.ListViewAdapter
import com.example.lists.adapter.RecyclerViewAdapter

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_list,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<ListView>(R.id.listView)
        var objects = listOf(
            Tour(1, "Egypt", "abc",20000),
            Tour(2, "Armenia", "abc",30000),
            Tour(3, "US", "abc",10000),
            Tour(4, "Poland", "abc",20000),
            Tour(5, "Portugal", "abc",30000),
            Tour(6, "Jordan", "abc",10000),
//            Tour(1, "Egypt", "https://pbs.twimg.com/profile_images/625819687100747776/PTMyImkS_400x400.jpg",20000),
//            Tour(2, "Armenia", "https://armenianweekly.com/wp-content/uploads/2019/10/31804754292_ff9f014051_o.jpg",30000),
//            Tour(3, "US", "https://images.unsplash.com/photo-1515112102038-7899a6b9db83?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80",10000),
//            Tour(4, "Poland", "https://www.planetware.com/wpimages/2020/03/poland-best-places-to-visit-krakow.jpg",20000),
//            Tour(5, "Portugal", "https://www.planetware.com/wpimages/2020/03/portugal-in-pictures-beautiful-places-to-photograph-lisbon.jpg",30000),
//            Tour(6, "Jordan", "https://www.borgenmagazine.com/wp-content/uploads/2017/11/36781616936_78ccfdc5ab_b.jpg",10000),
            )

        listView.adapter = ListViewAdapter(
            context = context ?: return,
            objects = objects
        )
        listView.setOnItemClickListener { _, _, position, id ->
            Toast.makeText(context, objects[position].title, Toast.LENGTH_SHORT).show()
        }

        val rvAdapter = RecyclerViewAdapter(
            objects = objects
        ){
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        }

        var dlAdapter = DynamicListAdapter(){
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        }
        dlAdapter.submitList(objects)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        //recyclerView.adapter = rvAdapter
        recyclerView.adapter = dlAdapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)


        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AddFragment.newInstance("ListFragment"))
                .addToBackStack(null)
                .commit()
        }

        parentFragmentManager.setFragmentResultListener("FR_RESULT", viewLifecycleOwner){
            _, bundle ->
            if(bundle.containsKey(RESULT_TITLE)){
               // Toast.makeText(context, "${bundle.getString(RESULT_TITLE)}", Toast.LENGTH_SHORT).show()
                   val tourTitle = bundle.getString(RESULT_TITLE).toString()
                val tour: Tour = Tour(1, tourTitle, "123", 124)
                dlAdapter = DynamicListAdapter(){
                    Toast.makeText(context, tour.title, Toast.LENGTH_SHORT).show()
                }
            //   val newList1 = objects.toMutableList().apply { removeAt(1) }.also { objects = it }
            //   dlAdapter.submitList(newList1)
            }
        }

        val btnChange = view.findViewById<Button>(R.id.btnChange)
        val btnRemove = view.findViewById<Button>(R.id.btnRemove)
        btnChange.setOnClickListener {
            //rvAdapter.updateList("aa")
            // rvAdapter.notifyItemChanged(2)
        }
        btnRemove.setOnClickListener {
            /* val TourDiffUtilCallback = TourDiffUtilCallback(
                 oldList = objects,
                 newList = rvAdapter.removeListElement()
             )
             val diffResult = DiffUtil.calculateDiff(TourDiffUtilCallback)
             diffResult.dispatchUpdatesTo(rvAdapter)*/
            val newList = objects.toMutableList().apply { removeAt(2) }.also { objects = it }
            dlAdapter.submitList(newList)
            //rvAdapter.notifyItemRemoved(2)
        }

    }

    companion object {
        fun newInstance() = ListFragment()
    }
}