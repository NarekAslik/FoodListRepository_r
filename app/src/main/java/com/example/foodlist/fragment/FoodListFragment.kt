package com.example.foodlist.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.example.foodlist.R
import com.example.foodlist.adapter.ProductFiltersAdapter
import com.example.foodlist.adapter.ProductTypesAdapter
import com.example.foodlist.data.ProductItemData
import com.example.foodlist.data.ProductTitlesData
import com.example.foodlist.databinding.FragmentListFoodBinding
import com.example.foodlist.intarface.FilterItemClickListener
import com.example.foodlist.intarface.ProductItemClickListener
import com.example.foodlist.viewmodel.FoodListViewModel
import kotlinx.coroutines.flow.collectLatest


class FoodListFragment : Fragment(R.layout.fragment_list_food), FilterItemClickListener,
    ProductItemClickListener {

    private lateinit var binding: FragmentListFoodBinding

    private var productFiltersAdapter = ProductFiltersAdapter()
    private var productTypesAdapter = ProductTypesAdapter()

    private val productListFoodListViewModel by lazy { FoodListViewModel(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getProductList()
        getTitlesList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListFoodBinding.bind(view)
        init()
        binding.basketFragmentImView.setOnClickListener() {
            findNavController().navigate(R.id.basketsFragment)
        }
        productListFoodListViewModel.updateFavoriteIcon()


    }

    private fun init() {
        binding.horizontalRcView.apply {
            layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
            adapter = productFiltersAdapter
        }
        binding.verticalRcView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productTypesAdapter
        }
        productFiltersAdapter.setFilterItemClickListener(this)
        productTypesAdapter.setProductItemClickListener(this)
    }

    override fun filterItemClick(type: ProductTitlesData) {
        productListFoodListViewModel.filterItemClick(type)
    }

    override fun setFavoriteItemClick(itemData: ProductItemData) {
        productListFoodListViewModel.setFavoriteItemClick(itemData)
    }

    override fun addToBasket(itemData: ProductItemData) {
        productListFoodListViewModel.addToBasket(itemData)
    }

    private fun getProductList() {
        productListFoodListViewModel.getProductList()
        lifecycleScope.launchWhenStarted {
            productListFoodListViewModel.productListFlow.collectLatest { productList ->
                productTypesAdapter.setProductTypesData(productList)
            }
        }
    }

    private fun getTitlesList() {
        lifecycleScope.launchWhenStarted {
            productListFoodListViewModel.titlesListFlow.collectLatest { titlesList ->
                productFiltersAdapter.setItemsTitleData(titlesList)
            }
        }
    }
}