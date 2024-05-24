package com.example.androidtestapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.androidtestapp.model.CharacterResponse
import retrofit2.http.Query

interface ApiService {
    /**
     * Retrieves the first page of characters.
     *
     * @return A [CharacterResponse] containing the list of characters and pagination information.
     */
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
    /**
     * Retrieves characters from a specific page.
     *
     * @param page The page number to retrieve characters from.
     * @return A [CharacterResponse] containing the list of characters and pagination information.
     */
    @GET("character")
    suspend fun charactersByPage(@Query("page") page: Int): CharacterResponse
}