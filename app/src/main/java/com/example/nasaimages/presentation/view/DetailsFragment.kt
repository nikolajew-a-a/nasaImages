package com.example.nasaimages.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.nasaimages.R
import com.example.nasaimages.data.model.Item
import com.example.nasaimages.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable(ITEM_VALUE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view.context)
            .load(item?.links?.get(0)?.href)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fitCenter()
            .into(binding.imageDetail)
        binding.descriptionText.text = item?.data?.get(0)?.description ?: "-"
    }

    companion object {
        private const val ITEM_VALUE = "item_value"
        fun newInstance(item: Item) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ITEM_VALUE, item)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}