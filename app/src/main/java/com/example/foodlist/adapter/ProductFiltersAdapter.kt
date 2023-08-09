package com.example.foodlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlist.R
import com.example.foodlist.data.ProductTitlesData
import com.example.foodlist.databinding.ProductFiltersItemBinding
import com.example.foodlist.intarface.FilterItemClickListener


class ProductFiltersAdapter : RecyclerView.Adapter<ProductFiltersAdapter.HorizontalItemHolder>() {
    private var selectedPosition = -1
    private var productListTitle = arrayListOf<ProductTitlesData>()
    private var filterItemClickListener: FilterItemClickListener? = null

    class HorizontalItemHolder(val binding: ProductFiltersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    fun setFilterItemClickListener(listener: FilterItemClickListener) {
        filterItemClickListener = listener
    }

    fun setItemsTitleData(list: List<ProductTitlesData>) {
        productListTitle.clear()
        productListTitle.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_filters_item, parent, false)
        return HorizontalItemHolder(ProductFiltersItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: HorizontalItemHolder, position: Int) {
        holder.binding.apply {
            val data = productListTitle[position]
            horizItemBtn.text = data.title
            horizItemBtn.setOnClickListener() {
                filterItemClickListener?.filterItemClick(data)
                val oldPosition = selectedPosition
                selectedPosition = position.toInt()
                notifyItemChanged(oldPosition)
                notifyItemChanged(selectedPosition)
            }
            val resId = if (position == selectedPosition) R.drawable.filter_selected_item_background
            else R.drawable.horiz_item_background
            horizItemBtn.background = ContextCompat.getDrawable(root.context, resId)
        }
    }

    override fun getItemCount(): Int {
        return productListTitle.size
    }
}