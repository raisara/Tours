package com.example.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

private const val LABEL_FROM = "LabelFrom"
const val RESULT_TITLE = "ResultTitle" //public?

class AddFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_add,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnClose).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        view.findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val title: String = view.findViewById<TextInputEditText>(R.id.inputTitle).text.toString()
            parentFragmentManager.setFragmentResult("FR_RESULT", bundleOf(RESULT_TITLE to title))

            parentFragmentManager.popBackStack()
        }


    }

    companion object {
        //fun newInstance() = AddFragment()
        fun newInstance(from: String) = AddFragment().apply {
            arguments = bundleOf(LABEL_FROM to from)
        }
    }
}