package com.example.foodlist.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodlist.data.IsFavoriteData
import com.example.foodlist.data.ItemData
import com.example.foodlist.data.ProductItemData
import com.example.foodlist.data.ProductTitlesData
import com.example.foodlist.database.MyDataBase
import com.example.foodlist.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FoodListViewModel(context: Context) : ViewModel() {
    private var db = MyDataBase.getDataBase(context)
    private val repository = Repository()
    private var allProductList = listOf<ProductItemData>()
    val productListFlow = MutableStateFlow<List<ProductItemData>>(arrayListOf())
    val titlesListFlow = MutableStateFlow<List<ProductTitlesData>>(arrayListOf())


    fun getProductList() {
        CoroutineScope(IO).launch {
            val data = repository.getProductsList().body()
            if (data != null) {
                productListFlow.value = data.products
                allProductList = data.products
                titlesListFlow.value = data.titles
            }
        }
    }


    fun filterItemClick(type: ProductTitlesData) {
        var newProductList = arrayListOf<ProductItemData>()
        if (type.title == "All")
            newProductList.addAll(allProductList)

        for (product in allProductList) {
            if (type.title == product.type) {
                newProductList.add(product)
            }
        }

        //// pass data to fragment
        productListFlow.value = newProductList
    }

    fun setFavoriteItemClick(itemData: ProductItemData) {
        CoroutineScope(IO).launch {
            if (itemData.isFavorite) {
                var item = IsFavoriteData(
                    name = itemData.name,
                    price = itemData.price,
                    image = itemData.image,
                    isFavorite = itemData.isFavorite
                )
                db.getFavoriteDao().insertFavoriteItem(item)
            } else {
                //todo remove item
                var item: IsFavoriteData = db.getFavoriteDao().getFavoriteItemByName(itemData.name)

                if (item == null) {
                    return@launch
                } else {
                    db.getFavoriteDao().deleteFavoriteItem(item)
                }
            }
        }
    }

    fun addToBasket(productItemData: ProductItemData) {
        CoroutineScope(IO).launch {
            var item: ItemData? = db.getDao().getItemByName(productItemData.name)
            if (item == null) {
                item = ItemData(
                    name = productItemData.name,
                    price = productItemData.price,
                    image = productItemData.image,
                    count = 1
                )
            } else {
                item.count = item.count + 1
            }
            db.getDao().insertItem(item)
        }
    }

    fun updateFavoriteIcon() {
        viewModelScope.launch {
            db.getFavoriteDao().getAllFavorites().collectLatest { favoriteList ->
                for (product in allProductList) {
                    product.isFavorite = false
                    for (isFavorite in favoriteList) {
                        if (product.name == isFavorite.name && product.price == isFavorite.price) {
                            product.isFavorite = true
                        }
                    }
                }
            }
        }
    }
}