package com.example.superheroes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("api/superheroes")
    suspend fun getSuperheroes(): List<Superheroe>

    @GET("api/superheroes/{id}")
    suspend fun getSuperheroes(@Path("id") id: Int): Superheroe

    @POST("api/superheroes")
    suspend fun postSuperheroes(@Body superheroes: Superheroe): Superheroe

    @PUT("api/superheroes/{id}")
    suspend fun putSuperheroes(@Body superheroes: Superheroe, @Path("id") id: Int): Superheroe

    @DELETE("api/superheroes/{id}")
    suspend fun deleteSuperheroes(@Path("id") id: Int): Superheroe

    companion object {
        @Volatile
        private var INSTANCE: ApiService? = null

        private var BASE_URL = "http://10.0.2.2:8000"

        fun getApiMamager(): ApiService {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
                INSTANCE = instance
                instance
            }
        }
    }
}