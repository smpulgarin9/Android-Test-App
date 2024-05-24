package com.example.androidtestapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * RetrofitClient is responsible for creating and providing the ApiService instance.
 */
object RetrofitClient {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    /**
     * Lazily initializes the [ApiService] instance.
     *
     * The [ApiService] is created using Retrofit with the base URL and a Gson converter factory
     * for JSON serialization and deserialization.
     */
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}