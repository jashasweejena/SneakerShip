package com.example.sneakership.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sneakership.data.local.sneaker.SneakerUiItem
import com.example.sneakership.databinding.FragmentHomeBinding
import com.example.sneakership.network.Resource
import com.example.sneakership.utils.gone
import com.example.sneakership.utils.hide
import com.example.sneakership.utils.isVisible
import com.example.sneakership.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SneakerHomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val sneakerListAdapter: SneakerListRecyclerViewAdapter by lazy { SneakerListRecyclerViewAdapter(::onSneakerItemClick) }

    private val viewModel: SneakersListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
//    private fun searchDatabase(query: String) {
//        val searchQuery = "%${query}%"
//        viewModel.searchSneakers(searchQuery).observe(viewLifecycleOwner) {
//            sneakerListAdapter.submitList(it)
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchSneakers()
        binding.rvList.apply {
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            adapter = sneakerListAdapter
        }

        binding.btnSearch.setOnClickListener {
            toggleSearchBarVisibility()
        }

        binding.etSearch.addTextChangedListener(
            onTextChanged = { charSequence: CharSequence?, _, _, _ ->
                val query = "%${charSequence}%"
                viewModel.searchSneakers(query)
            },
        )

        viewModel.sneakersLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    sneakerListAdapter.submitList(it.data)
                }
                is Resource.Loading -> {
                    handleLoaderVisibility(it.isLoading)
                }
                is Resource.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.sneakersLiveData
    }

    private fun toggleSearchBarVisibility() {
        val isVisible = binding.etSearch.isVisible()
        if (isVisible) {
            binding.tvLogo.show()
            binding.etSearch.hide()
        } else {
            binding.etSearch.show()
            binding.tvLogo.hide()
        }
    }

    private fun handleLoaderVisibility(shouldShow: Boolean) {
        if (shouldShow) {
            binding.progressCircular.show()
        } else {
            binding.progressCircular.gone()
        }
    }

    private fun onSneakerItemClick(item: SneakerUiItem) {
        val action = SneakerHomeFragmentDirections.actionNavigationHomeToNavigationSneakerDetails(item)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}