package com.viktorija.bakingapp.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Recipe(

    val id: Long,

    @Json(name = "name")
    val title: String,

    val servings: Int,

    val ingredients: List<Ingredient>,

    val steps: List<Step>,

    val image: String

) : Parcelable