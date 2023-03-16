package com.example.sneakership.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import androidx.recyclerview.widget.SnapHelper
import com.example.sneakership.R
import com.example.sneakership.data.local.cart.CartItem
import com.example.sneakership.databinding.FragmentSneakerDetailsBinding
import com.example.sneakership.ui.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SneakerDetailsFragment : Fragment(), OnSnapPositionChangeListener, SneakerAdapterCallbacks {

    private var _binding: FragmentSneakerDetailsBinding? = null

    private var currentPosition = -1

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: SneakerDetailsFragmentArgs by navArgs()

    private val cartViewModel: CartViewModel by viewModels()

    private val listAdapter = SneakerDetailsImageRecyclerViewAdapter(this)

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

        binding.btnAddToCart.setOnClickListener { cartViewModel.insertCartItem(
            CartItem(
            System.currentTimeMillis(), args.sneaker.id, args.sneaker.name, args.sneaker.retailPrice.toDouble(), 1,
                args.sneaker.media.smallImageUrl
        )
        ) }

        binding.rvShoes.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            val snapHelper = LinearSnapHelper() // Or PagerSnapHelper
            attachSnapHelperWithListener(snapHelper, onSnapPositionChangeListener = this@SneakerDetailsFragment)
            adapter = listAdapter
        }

        binding.ivForward.setOnClickListener {
            if (currentPosition == NO_POSITION) return@setOnClickListener
            if (currentPosition + 1 < 3) {
                binding.rvShoes.smoothScrollToPosition(currentPosition + 1)
            }
        }

        binding.ivBack.setOnClickListener {
            if (currentPosition == NO_POSITION) return@setOnClickListener
            if (currentPosition - 1 >= 0) {
                binding.rvShoes.smoothScrollToPosition(currentPosition - 1)
            }
        }

        listAdapter.submitList(listOf("https://i.imgur.com/elKbMkt.png", "https://i.imgur.com/elKbMkt.png", "https://i.imgur.com/elKbMkt.png"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSnapPositionChange(position: Int) {
        currentPosition = position
        when (position) {
            0 -> {
                context?.let { binding.firstStepper.setBackgroundColor(ContextCompat.getColor(it, R.color.black))
                    binding.secondStepper.setBackgroundColor(ContextCompat.getColor(it, R.color.grey))
                    binding.thirdStepper.setBackgroundColor(ContextCompat.getColor(it, R.color.grey)) }
            }
            1 -> {
                context?.let {
                    binding.firstStepper.setBackgroundColor(ContextCompat.getColor(it, R.color.black))
                    binding.secondStepper.setBackgroundColor(ContextCompat.getColor(it, R.color.black))
                    binding.thirdStepper.setBackgroundColor(ContextCompat.getColor(it, R.color.grey))
                }
            }
            2 -> {
                context?.let {
                    binding.firstStepper.setBackgroundColor(ContextCompat.getColor(it, R.color.black))
                    binding.secondStepper.setBackgroundColor(ContextCompat.getColor(it, R.color.black))
                    binding.thirdStepper.setBackgroundColor(ContextCompat.getColor(it, R.color.black))
                }
            }
            else -> {}
        }
    }

    override fun onBackClick() {

    }

    override fun onForwardClick() {

    }
}