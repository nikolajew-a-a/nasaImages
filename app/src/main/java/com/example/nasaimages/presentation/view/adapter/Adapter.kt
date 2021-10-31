package com.example.nasaimages.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaimages.R
import com.example.nasaimages.data.model.Item
import com.example.nasaimages.presentation.MainActivity
import com.example.nasaimages.presentation.view.DetailsFragment
import com.squareup.picasso.Picasso

class Adapter(private var items: List<Item>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_view)
        private var item: Item? = null

        fun bind(item: Item) {
            this.item = item
            Picasso.get()
                .load(item.links?.get(0)?.href)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView)
        }

        init {
            itemView.setOnClickListener {
                item?.let {
                    (itemView.context as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, DetailsFragment.newInstance(it))
                        .commit()
                }
            }
        }
    }
}
