package com.example.foodlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Favorites")
data class IsFavoriteData(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "Price")
    var price: Int,
    @ColumnInfo(name = "image_url")
    var image: String,

){
    override fun hashCode(): Int {
        return super.hashCode()
    }
    override fun equals(other: Any?): Boolean {
        return if (other is IsFavoriteData) {
            name == other.name && price == other.price
        } else false
    }
}
