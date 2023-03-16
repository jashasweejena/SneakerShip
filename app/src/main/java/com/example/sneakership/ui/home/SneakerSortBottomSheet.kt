package com.example.sneakership.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.sneakership.R
import com.example.sneakership.databinding.LayoutSortBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SneakerSortBottomSheet : BottomSheetDialogFragment(R.layout.layout_sort_bottomsheet) {
    private lateinit var binding: LayoutSortBottomsheetBinding

    companion object {
        const val REQUEST_KEY = "SneakerSortBottomSheet"
        const val BUNDLE_KEY_SORT_ORDER = "bundle_key_sort_order"

        private const val TAG = "SneakerSortBottomSheet"

        fun show(supportFragmentManager: FragmentManager) {
            val instance = SneakerSortBottomSheet() as? BottomSheetDialogFragment
            instance?.show(supportFragmentManager, TAG)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = LayoutSortBottomsheetBinding.bind(view)

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(i) {
                R.id.rb_name -> {
                    val bundle = Bundle().apply {
                        putSerializable(BUNDLE_KEY_SORT_ORDER, SortOrder.BY_NAME)
                    }
                    setFragmentResult(REQUEST_KEY, bundle)
                    dismiss()
                }

                R.id.rb_price -> {
                    val bundle = Bundle().apply {
                        putSerializable(BUNDLE_KEY_SORT_ORDER, SortOrder.BY_RETAIL_PRICE)
                    }
                    setFragmentResult(REQUEST_KEY, bundle)
                    dismiss()
                }
            }
        }
    }
}