package com.example.sneakership.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sneakership.data.local.sneaker.SneakerUiItem
import com.example.sneakership.databinding.FragmentHomeBinding
import com.example.sneakership.network.Resource
import com.example.sneakership.network.sneaker.Media
import com.example.sneakership.network.sneaker.SneakersListDtoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SneakerHomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val sneakerListAdapter: SneakerListRecyclerViewAdapter by lazy { SneakerListRecyclerViewAdapter(::onSneakerItemClick) }

    private val viewModel: SneakersListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(SneakersListViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchSneakers()
        binding.rvList.apply {
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            adapter = sneakerListAdapter
        }

        viewModel.sneakersLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    sneakerListAdapter.submitList(it.data)
                }
                is Resource.Loading -> {}
                is Resource.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
//        sneakerListAdapter.submitList(
//            arrayListOf(
//                SneakersListDtoItem(
//                    "Nike",
//                    listOf(),
//                    "Male",
//                    "3131313",
//                    Media(
//                        "", "", ""
//                    ),
//                    "Super Jordan",
//                    "25-12-2000",
//                    1221,
//                    listOf(),
//                    "fsdf"
//                ),
//
//                SneakersListDtoItem(
//                    "Nike",
//                    listOf(),
//                    "Male",
//                    "3131313",
//                    Media(
//                        "", "", ""
//                    ),
//                    "Super Hike",
//                    "25-12-2000",
//                    1221,
//                    listOf(),
//                    "fsdf"
//                ),
//
//                SneakersListDtoItem(
//                    "Nike",
//                    listOf(),
//                    "Male",
//                    "3131313",
//                    Media(
//                        "", "", ""
//                    ),
//                    "Super Hike",
//                    "25-12-2000",
//                    1221,
//                    listOf(),
//                    "fsdf"
//                )
//            )
//        )

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