package com.example.sneakership.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sneakership.databinding.ItemSneakersListBinding
import com.example.sneakership.ui.home.data.SneakersListDtoItem

class SneakerListRecyclerViewAdapter : ListAdapter<SneakersListDtoItem, SneakerListRecyclerViewAdapter.ViewHolder>(object:
    DiffUtil.ItemCallback<SneakersListDtoItem>() {
    override fun areItemsTheSame(
        oldItem: SneakersListDtoItem,
        newItem: SneakersListDtoItem
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: SneakersListDtoItem,
        newItem: SneakersListDtoItem
    ): Boolean {
        return oldItem == newItem
    }

}){
    class ViewHolder(private val binding: ItemSneakersListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SneakersListDtoItem) {
            binding.item = item
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSneakersListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}