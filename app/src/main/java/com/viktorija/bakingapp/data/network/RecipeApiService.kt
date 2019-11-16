package com.viktorija.bakingapp.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.viktorija.bakingapp.data.model.Recipe
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://d17h27t6h515a5.cloudfront.net"

// Moshi parses this JSON data and converts it into Kotlin objects
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


// Using a Retrofit builder to create a Retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

// Defining an interface that defines how Retrofit talks to the web server using HTTP requests
interface RecipeApiService {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    fun getRecipes():
            Deferred<List<Recipe>>
}

// Creating RecipeApi object using Retrofit to implement the RecipeApiService
// retrofit.create method creates Retrofit service itself with the RecipeApiService interface
object RecipeApi {
    val retrofitService: RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java) }
}