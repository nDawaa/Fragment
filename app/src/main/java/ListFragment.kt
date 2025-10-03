package com.example.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragment.databinding.FragmentListBinding

class ListFragment : Fragment() {
    interface OnItemSelectedListener {
        fun onItemSelected(item: String)
    }

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var listener: OnItemSelectedListener? = null
    private val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")



    // Make sure to import android.content.Context



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnItemSelectedListener")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = ItemAdapter(items) { item ->
            listener?.onItemSelected(item)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}