package com.example.foodlist.data

data class ProductItemData(
    val type: String,
    val image: String,
    val name: String,
    val price: Int,
    var isFavorite: Boolean = false

)
