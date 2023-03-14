package com.example.sneakership.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.sneakership.data.local.CartDao
import com.example.sneakership.data.local.CartItem
import com.example.sneakership.data.local.SneakerDatabase
import com.example.sneakership.databinding.FragmentSneakerDetailsBinding
import com.example.sneakership.ui.cart.CartViewModel
import com.example.sneakership.ui.cart.CartViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SneakerDetailsFragment : Fragment() {

    private var _binding: FragmentSneakerDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: SneakerDetailsFragmentArgs by navArgs()

    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(SneakerDetailsViewModel::class.java)

        _binding = FragmentSneakerDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.item = args.sneaker

        binding.btnAddToCart.setOnClickListener { cartViewModel?.insertCartItem(CartItem(
            System.currentTimeMillis(), 234L, args.sneaker.name, args.sneaker.retailPrice.toDouble(), 1
        )) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}