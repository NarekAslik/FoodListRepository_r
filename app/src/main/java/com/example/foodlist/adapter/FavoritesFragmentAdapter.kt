package com.example.foodlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodlist.R
import com.example.foodlist.data.IsFavoriteData
import com.example.foodlist.data.ProductItemData
import com.example.foodlist.databinding.FavoriteItemLayoutBinding
import com.example.foodlist.intarface.FavoritesItemClickListener

class FavoritesFragmentAdapter :
    RecyclerView.Adapter<FavoritesFragmentAdapter.FavoritesFragmentHolder>() {
    private var productTypesFromDbList = arrayListOf<IsFavoriteData>()
    private var favoritesItemClickListener: FavoritesItemClickListener? = null

    class FavoritesFragmentHolder(val binding: FavoriteItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    fun setFavoritesItemClickListener(listener: FavoritesItemClickListener) {
        favoritesItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesFragmentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_item_layout, parent, false)
        return FavoritesFragmentHolder(FavoriteItemLayoutBinding.bind(view))
    }

    override fun onBindViewHolder(holder: FavoritesFragmentHolder, position: Int) {
        val data = productTypesFromDbList[position]
        holder.binding.apply {
            Glide.with(productImage)
                .load(data.image)
                .centerCrop()
                .into(productImage)
            productName.text = data.name
            productPrice.text = data.price.toString()
            checkBoxFavorite.setImageResource(R.drawable.ic_favorite_filled)
            checkBoxFavorite.setOnClickListener() {
                favoritesItemClickListener?.deleteItem(data)
                productTypesFromDbList.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return productTypesFromDbList.size
    }

    fun setFavoriteProductsFromDb(list: List<IsFavoriteData>) {
        productTypesFromDbList.clear()
        productTypesFromDbList.addAll(list)
        notifyDataSetChanged()
    }
}