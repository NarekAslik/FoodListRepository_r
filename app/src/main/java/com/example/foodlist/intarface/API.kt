package com.example.foodlist.intarface

import com.example.foodlist.data.Products
import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("b/P6R1")
    suspend fun getProductsList(): Response<Products>
}