package com.example.sneakership.feature.cart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakership.feature.cart.domain.models.CartItem
import com.example.sneakership.databinding.ItemCartListBinding
import com.example.sneakership.feature.cart.presentation.adapter.CartAdapter.CartViewHolder

class CartAdapter(private val cartClickedListeners: CartClickedListeners) :
    ListAdapter<CartItem, CartViewHolder>(object: DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }

    }) {
    private var shoeCartList: List<CartItem>? = null
    fun setShoeCartList(shoeCartList: List<CartItem>?) {
        this.shoeCartList = shoeCartList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartListBinding.inflate(inflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CartViewHolder(private val binding: ItemCartListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shoeCart: CartItem) {
            binding.eachCartItemName.text = shoeCart.name
            binding.eachCartItemQuantityTV.text = shoeCart.quantity.toString() + ""
            binding.eachCartItemPriceTv.text = "" + shoeCart.price
            binding.btnRemove.setOnClickListener { cartClickedListeners.onDeleteClicked(shoeCart) }
            binding.eachCartItemAddQuantityBtn.setOnClickListener { cartClickedListeners.onPlusClicked(shoeCart) }
            binding.eachCartItemMinusQuantityBtn.setOnClickListener { cartClickedListeners.onMinusClicked(shoeCart) }

            Glide.with(binding.root.context)
                .load(shoeCart.imageUrl)
                .into(binding.eachCartItemIV)
        }
    }

    interface CartClickedListeners {
        fun onDeleteClicked(shoeCart: CartItem?)
        fun onPlusClicked(shoeCart: CartItem?)
        fun onMinusClicked(shoeCart: CartItem?)
    }
}