package com.example.foodlist.intarface

import com.example.foodlist.data.IsFavoriteData

interface FavoritesItemClickListener {
    fun deleteItem(itemData: IsFavoriteData)
}