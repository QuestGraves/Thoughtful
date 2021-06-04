package com.keltica.thoughtful.view_model.view.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.keltica.thoughtful.view_model.MainViewModel
import com.keltica.thoughtful.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        //ToDo live data setup here,,,roughing in the framework for the app architecture.
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}