package com.example.leafy.mvvm.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // private const val BASE_URL = "http://192.168.56.1:3000/" - CELULAR

    // private const val BASE_URL = "http://10.0.2.2:3000/" // Emulador

    private const val BASE_URL = "http://127.0.0.1:3000/"

    val api: LeafyApi by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LeafyApi::class.java)
    }
}