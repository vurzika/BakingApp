package com.viktorija.bakingapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

// The Parcelable interface enables objects to be serialized, so that the objects' data can be
// passed around between fragments or activities. In this case, for the data inside the MarsProperty
// object to be passed to the detail fragment via Safe Args, MarsProperty must implement the Parcelable interface.
// Add @Parcelize annotation to MarsProperty and have it implement Parcelable

@Parcelize
data class Ingredient(

    @Json(name = "ingredient")
    val name: String,

    val quantity: Double,

    val measure: String

) : Parcelable