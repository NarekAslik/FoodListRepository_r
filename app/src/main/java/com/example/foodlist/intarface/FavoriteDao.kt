package com.example.foodlist.intarface

import androidx.room.*
import com.example.foodlist.data.IsFavoriteData
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteItem(isFavoriteData: IsFavoriteData)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<IsFavoriteData>>

    @Delete
    fun deleteFavoriteItem(itemData: IsFavoriteData)

    @Query("SELECT * FROM Favorites WHERE name LIKE :user_name")
    fun getFavoriteItemByName(user_name: String): IsFavoriteData
}