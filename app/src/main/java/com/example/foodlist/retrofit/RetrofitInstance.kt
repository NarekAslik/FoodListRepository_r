package com.example.foodlist.retrofit

import com.example.foodlist.intarface.API
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    val retrofit by lazy {

        val builder = OkHttpClient.Builder()
        builder.hostnameVerifier { hostname, session -> true }

        Retrofit.Builder()
            .baseUrl("https://jsonkeeper.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
    }
    val api: API by lazy {
        retrofit.create(API::class.java)
    }
}