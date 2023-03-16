package com.example.sneakership.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakership.data.local.cart.CartItem
import com.example.sneakership.databinding.FragmentNotificationsBinding
import com.example.sneakership.utils.gone
import com.example.sneakership.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val cartAdapter = CartAdapter(object: CartAdapter.CartClickedListeners {
            override fun onDeleteClicked(item: CartItem?) {
                item?.let {
                    cartViewModel.deleteCartItem(it)
                }
            }

            override fun onPlusClicked(item: CartItem?) {
                item?.let {
                    cartViewModel.incrementCartItem(it)
                }
            }

            override fun onMinusClicked(item: CartItem?) {
                item?.let {
                    cartViewModel.decrementCartItem(it)
                }
            }

        })

        binding.tvTaxesValue.text = "\$ ${cartViewModel.taxesAndCharges}"

        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = cartAdapter
        }

        cartViewModel.cartItems.observe(viewLifecycleOwner) {
            cartAdapter.submitList(it)
        }

        cartViewModel.total.observe(viewLifecycleOwner) {
            it?.let {
                if (it != 0) {
                    binding.groupTax.show()
                    binding.tvTaxesValue.text = "\$ ${cartViewModel.taxesAndCharges}"
                    binding.tvTotalValue.text = "\$ $it"
                }

            } ?: run {
                binding.groupTax.gone()
            }
        }

        cartViewModel.subTotal.observe(viewLifecycleOwner) {
            it?.let {
                if (it != 0) {
                    binding.groupTax.show()
                    binding.tvSubtotalValue.text = "\$ $it"
                }
            } ?: run {
                binding.groupTax.gone()
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}