package com.example.androidbootcamp10yul.ui.home

import androidx.fragment.app.viewModels
import com.example.androidbootcamp10yul.adapter.ProductAdapter
import com.example.androidbootcamp10yul.base.BaseFragment
import com.example.androidbootcamp10yul.databinding.FragmentHomeBinding


class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModels<HomeViewModel>()
    private val productAdapter = ProductAdapter()

    override fun createFinished() {
        setRecycler()
        viewModel.getProducts()
    }

    override fun observeEvents() {
        viewModel.data.observe(viewLifecycleOwner) {
            productAdapter.differ.submitList(it)
        }
    }

    private fun setRecycler() {
        binding.rvHome.adapter = productAdapter
    }

}