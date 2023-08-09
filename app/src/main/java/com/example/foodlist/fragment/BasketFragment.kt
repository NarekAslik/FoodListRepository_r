package com.example.foodlist.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodlist.Dialog
import com.example.foodlist.R
import com.example.foodlist.adapter.BasketFragmentAdapter
import com.example.foodlist.data.ItemData
import com.example.foodlist.databinding.FragmentBasketBinding
import com.example.foodlist.intarface.BasketItemClickListener
import com.example.foodlist.viewmodel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

const val DELETE_ITEM_DATA = "delete_item_data"


class BasketFragment : Fragment(R.layout.fragment_basket), BasketItemClickListener {

    private lateinit var binding: FragmentBasketBinding
    private val basketViewModel by lazy { BasketViewModel(requireContext()) }
    private val basketFragmentAdapter = BasketFragmentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBasketBinding.bind(view)
        init()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            basketViewModel.getBasketData().collectLatest {
                // set data to adapter
                basketFragmentAdapter.setProductProductTypesFromDb(it)
            }
        }
        binding.deleteAllBtn.setOnClickListener {
            dialogShow()
        }
        binding.imBack.setOnClickListener {
            findNavController().popBackStack()
        }
        //  to lear and watching videos about this
        activity?.supportFragmentManager?.setFragmentResultListener(
            DELETE_ITEM_DATA,
            viewLifecycleOwner
        ) { key, _ ->
            if (key == DELETE_ITEM_DATA) {
                basketViewModel.deleteAllItems()
            }
        }
    }

    private fun init() {
        binding.basketRcView.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = basketFragmentAdapter
        }
        basketFragmentAdapter.setBasketItemClickListener(this)
    }

    override fun deleteItem(productItemData: ItemData) {
        basketViewModel.deleteBasketData(productItemData)
    }

    override fun plusItemCount(itemData: ItemData) {
        basketViewModel.plusItemCount(itemData)
    }

    override fun minusItemCount(itemData: ItemData) {
        basketViewModel.minusItemCount(itemData)
    }

    private fun dialogShow() {
        Dialog().show(parentFragmentManager, "MyCustomFragment")
    }
}