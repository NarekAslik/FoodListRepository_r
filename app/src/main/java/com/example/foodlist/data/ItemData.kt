package com.example.foodlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemData(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "Price")
    var price: Int,
    @ColumnInfo(name = "image_url")
    var image: String,
    @ColumnInfo(name = "count")
    var count: Int
)
