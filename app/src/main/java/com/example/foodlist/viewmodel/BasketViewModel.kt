package com.example.foodlist.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodlist.data.ItemData
import com.example.foodlist.database.MyDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class BasketViewModel(context: Context) : ViewModel() {
    private val db = MyDataBase.getDataBase(context)

    fun getBasketData() = db.getDao().getAllItems()

    fun deleteBasketData(productItemData: ItemData) {
        CoroutineScope(IO).launch {
            db.getDao().deleteItem(productItemData)
        }

    }

    fun plusItemCount(itemData: ItemData) {
        CoroutineScope(IO).launch {
            itemData.count = itemData.count + 1
            db.getDao().insertItem(itemData)
        }
    }

    fun minusItemCount(itemData: ItemData) {
        CoroutineScope(IO).launch {
            itemData.count = itemData.count - 1
            if (itemData.count < 1) {
                itemData.count = 1
            } else db.getDao().insertItem(itemData)

        }
    }

    fun deleteAllItems() {
      viewModelScope.launch{
          db.getDao().deleteAllItems()
      }
    }
}