package com.example.foodlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.foodlist.R
import com.example.foodlist.data.ItemData
import com.example.foodlist.databinding.BasketItemBinding
import com.example.foodlist.intarface.BasketItemClickListener



class BasketFragmentAdapter : RecyclerView.Adapter<BasketFragmentAdapter.BasketFragmentHolder>() {

    private var productTypesFromDbList = arrayListOf<ItemData>()
    private var basketItemClickListener: BasketItemClickListener? = null

    class BasketFragmentHolder(val binding: BasketItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    fun setBasketItemClickListener(listener: BasketItemClickListener) {
        basketItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketFragmentHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.basket_item, parent, false)
        return BasketFragmentHolder(BasketItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: BasketFragmentHolder, position: Int) {
        holder.binding.apply {
            val data = productTypesFromDbList[position]
            nameTView.text = data.name
            priceTView.text = data.price.toString() + " AMD"
            Glide.with(productImage)
                .load(data.image)
                .centerCrop()
                .into(productImage)
            deleteImView.setOnClickListener() {
                basketItemClickListener?.deleteItem(data)
                productTypesFromDbList.removeAt(position)
                notifyDataSetChanged()
            }
            quantityLayout.tvCount.text = data.count.toString()
            quantityLayout.imPlus.setOnClickListener() {
                basketItemClickListener?.plusItemCount(data)
            }
            quantityLayout.imMinus.setOnClickListener() {
                basketItemClickListener?.minusItemCount(data)
            }

        }
    }

    override fun getItemCount(): Int {
        return productTypesFromDbList.size
    }

    fun setProductProductTypesFromDb(list: List<ItemData>) {
        productTypesFromDbList.clear()
        productTypesFromDbList.addAll(list)
        notifyDataSetChanged()
    }

}