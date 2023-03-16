package com.example.sneakership.feature.home.data.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SneakersListDtoItem(
    @SerializedName("brand")
    val brand: String,
    @SerializedName("colors")
    val colors: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("media")
    val media: Media,
    @SerializedName("name")
    val name: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("retailPrice")
    val retailPrice: Int,
    @SerializedName("sizes")
    val sizes: List<Int>,
    @SerializedName("styleId")
    val styleId: String
) : Parcelable

@Parcelize
data class Media(
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("smallImageUrl")
    val smallImageUrl: String,
    @SerializedName("thumbUrl")
    val thumbUrl: String
) :  Parcelable