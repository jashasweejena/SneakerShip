package com.example.sneakership.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakership.databinding.ItemSneakerImageBinding


class SneakerDetailsImageRecyclerViewAdapter(private val listener: SneakerAdapterCallbacks) : ListAdapter<String, SneakerDetailsImageRecyclerViewAdapter.ViewHolder>(
    object: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
           return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
) {
    class ViewHolder(private val binding: ItemSneakerImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, listener: SneakerAdapterCallbacks) {
            Glide.with(binding.root.context)
                .load(item)
                .into(binding.ivShoe)

//            binding.ivBack.setOnClickListener { listener.onBackClick() }
//            binding.ivForward.setOnClickListener { listener.onForwardClick() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSneakerImageBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

interface SneakerAdapterCallbacks {
    fun onBackClick()
    fun onForwardClick()
}