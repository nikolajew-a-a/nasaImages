package com.example.nasaimages.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.nasaimages.R
import com.example.nasaimages.data.model.Item
import com.example.nasaimages.presentation.MainActivity
import com.example.nasaimages.presentation.viewmodel.MainViewModel


class DetailsFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    private var param1: Int = -1
    private lateinit var imageView: ImageView
    private lateinit var description: TextView
    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(POSITION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (activity as MainActivity).getMainActivityComponent().getMainViewModel()

        item = mainViewModel.items.value!![param1]

        imageView = view.findViewById(R.id.image_detail)
        description = view.findViewById(R.id.description_text)

        Glide.with(view.context)
            .load(item.links?.get(0)?.href)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fitCenter()
            .into(imageView)
        description.text = item.data?.get(0)?.description ?: "-"
    }

    companion object {
        private const val POSITION = "position"
        fun newInstance(param1: Int) =
                DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putInt(POSITION, param1)
                    }
                }
    }
}