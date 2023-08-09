package com.example.foodlist.intarface

import com.example.foodlist.data.ProductItemData

interface ProductItemClickListener {
    fun setFavoriteItemClick(itemData: ProductItemData)
    fun addToBasket(itemData: ProductItemData)
}