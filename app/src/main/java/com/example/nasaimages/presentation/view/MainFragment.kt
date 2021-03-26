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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nasaimages.R
import com.example.nasaimages.data.model.Item
import com.example.nasaimages.presentation.MainActivity
import com.example.nasaimages.presentation.view.adapter.Adapter
import com.example.nasaimages.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private var items = ArrayList<Item>()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).getMainActivityComponent().getMainViewModel()

        progressBar = view.findViewById(R.id.progress_bar)
        recyclerView = view.findViewById(R.id.recycler_view)

        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.setItemViewCacheSize(100);
        adapter = Adapter(items)
        recyclerView.adapter = adapter

        mainViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if(it) {
                recyclerView.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
            }
        })
        mainViewModel.items.observe(viewLifecycleOwner, Observer { list: List<Item> -> adapter.setItems(list) })
        mainViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if (it.first) {
                Toast.makeText(this.activity, it.second, Toast.LENGTH_LONG).show()
                mainViewModel.errorMessageDisplayed()
            }
        })

        mainViewModel.getImages()

    }

    companion object {
        fun newInstance() =
            MainFragment()
    }
}