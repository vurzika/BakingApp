package com.viktorija.bakingapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Step (

    val id: Long,

    val description: String

) : Parcelable
