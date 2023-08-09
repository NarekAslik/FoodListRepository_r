package com.example.foodlist.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodlist.R
import com.example.foodlist.adapter.FavoritesFragmentAdapter
import com.example.foodlist.data.IsFavoriteData
import com.example.foodlist.databinding.FragmentFavoritesLayoutBinding
import com.example.foodlist.intarface.FavoritesItemClickListener
import com.example.foodlist.viewmodel.FavoriteViewModel
import kotlinx.coroutines.flow.collectLatest

class FavoritesFragment : Fragment(R.layout.fragment_favorites_layout),FavoritesItemClickListener {
    private var favoritesFragmentAdapter = FavoritesFragmentAdapter()
    private val favoriteViewModel by lazy { FavoriteViewModel(requireContext()) }
    private lateinit var binding: FragmentFavoritesLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesLayoutBinding.bind(view)
        init()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            favoriteViewModel.getAllIFavoritesFromDb().collectLatest {
                favoritesFragmentAdapter.setFavoriteProductsFromDb(it)
            }
        }
        favoritesFragmentAdapter.setFavoritesItemClickListener(this)
    }

    private fun init() {
        binding.favoriteFragmentRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = favoritesFragmentAdapter
        }

    }

    override fun deleteItem(itemData: IsFavoriteData) {
        favoriteViewModel.deleteFavoriteItemFromDb(itemData)
    }
}