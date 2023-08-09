package com.example.foodlist.intarface

import com.example.foodlist.data.ItemData

interface BasketItemClickListener {
    fun deleteItem(productItemData: ItemData)
    fun plusItemCount(itemData: ItemData)
    fun minusItemCount(itemData: ItemData)
}