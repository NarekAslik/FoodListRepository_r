package com.example.foodlist.intarface

import android.content.ClipData.Item
import androidx.room.*
import com.example.foodlist.data.IsFavoriteData
import com.example.foodlist.data.ItemData
import kotlinx.coroutines.flow.Flow


@Dao
interface BasketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(itemData: ItemData)

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<ItemData>>

    @Delete
    fun deleteItem(itemData: ItemData)

    @Query("DELETE FROM items ")
    suspend fun deleteAllItems()

    @Query("SELECT * FROM items WHERE name LIKE :user_name")
    fun getItemByName(user_name: String): ItemData
}