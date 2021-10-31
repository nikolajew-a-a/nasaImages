package com.example.nasaimages.presentation.view

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nasaimages.R
import com.example.nasaimages.data.model.Item
import com.example.nasaimages.databinding.FragmentDetailsBinding
import com.example.nasaimages.databinding.MainFragmentBinding
import com.example.nasaimages.di.viewmodel.injectViewModel
import com.example.nasaimages.presentation.MainActivity
import com.example.nasaimages.presentation.view.adapter.Adapter
import com.example.nasaimages.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by injectViewModel()

    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter: Adapter

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = Adapter(emptyList())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setItemViewCacheSize(100);
        binding.recyclerView.adapter = adapter

        mainViewModel.isLoading.observe(viewLifecycleOwner, {
            binding.recyclerView.isVisible = !it
            binding.progressBar.isVisible = it
        })
        mainViewModel.items.observe(viewLifecycleOwner,
            { list: List<Item> -> adapter.setItems(list) })
        mainViewModel.errorMessage.observe(viewLifecycleOwner, {
            if (it.first) {
                Toast.makeText(this.activity, it.second, Toast.LENGTH_LONG).show()
                mainViewModel.errorMessageDisplayed()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}