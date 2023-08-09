package com.example.foodlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodlist.R
import com.example.foodlist.data.ItemData
import com.example.foodlist.data.ProductItemData
import com.example.foodlist.databinding.ProductTypeItemBinding
import com.example.foodlist.intarface.ProductItemClickListener

class ProductTypesAdapter : RecyclerView.Adapter<ProductTypesAdapter.ProductTypesHolder>() {
    private var productTypesList = arrayListOf<ProductItemData>()
    private var productItemClickListener: ProductItemClickListener? = null


    fun setProductItemClickListener(listener: ProductItemClickListener) {
        productItemClickListener = listener
    }

    class ProductTypesHolder(val binding: ProductTypeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTypesHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_type_item, parent, false)
        return ProductTypesHolder(ProductTypeItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ProductTypesHolder, position: Int) {
        val data = productTypesList[position]
        holder.binding.apply {
            Glide.with(productImage)
                .load(data.image)
                .centerCrop()
                .into(productImage)
            productName.text = data.name
            val textPrice = data.price.toString() + " AMD"
            productPrice.text = textPrice
            checkBoxFavorite.updateFavoriteIcon(data.isFavorite)
            checkBoxFavorite.setOnClickListener {
                data.isFavorite = !data.isFavorite
                checkBoxFavorite.updateFavoriteIcon(data.isFavorite)
                productItemClickListener?.setFavoriteItemClick(data)
            }
            basketIm.setOnClickListener() {
                productItemClickListener?.addToBasket(data)
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return productTypesList.size
    }

    fun setProductTypesData(list: List<ProductItemData>) {
        productTypesList.clear()
        productTypesList.addAll(list)
        notifyDataSetChanged()
    }


    private fun ImageView.updateFavoriteIcon(isFavorite: Boolean) {
        val resId =
            if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outlined
        setImageResource(resId)
    }
}