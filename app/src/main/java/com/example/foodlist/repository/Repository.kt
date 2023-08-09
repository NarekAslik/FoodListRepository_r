package com.example.foodlist.repository

import com.example.foodlist.data.Products
import com.example.foodlist.retrofit.RetrofitInstance
import retrofit2.Response

class Repository {

    private val service = RetrofitInstance.api

    suspend fun getProductsList(): Response<Products> {
        return service.getProductsList()
    }
}