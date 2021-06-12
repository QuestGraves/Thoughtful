package com.keltica.thoughtful.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.keltica.thoughtful.databinding.FragmentSearchPortalBinding

class SearchPortalFragment : Fragment() {

    private lateinit var searchPortalViewModel: SearchPortalViewModel
   private var _binding: FragmentSearchPortalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

 private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchPortalViewModel =
            ViewModelProvider(this).get(SearchPortalViewModel::class.java)

        _binding = FragmentSearchPortalBinding.inflate(inflater, container, false)
     val root: View = binding.root
//         val recyclerView: RecyclerView = binding.
//
//        searchPortalViewModel.text.observe(viewLifecycleOwner, Observer {
//            //ToDo This is a compilation hack, make sure to implement your RecyclerAdapter!
//            recyclerView.adapter
//        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}