package com.example.foodlist.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.foodlist.data.IsFavoriteData
import com.example.foodlist.database.MyDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FavoriteViewModel(context: Context) : ViewModel() {
    private val db = MyDataBase.getDataBase(context)


    fun getAllIFavoritesFromDb() = db.getFavoriteDao().getAllFavorites()

    fun deleteFavoriteItemFromDb(itemData: IsFavoriteData) {
        CoroutineScope(IO).launch {
            db.getFavoriteDao().deleteFavoriteItem(itemData)
        }

    }


}