package com.example.sneakership.data.local.sneaker

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sneakership.network.sneaker.Media
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "sneaker_table")
@Parcelize
data class SneakerUiItem(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("colors")
    val colors: List<String>,
    @SerializedName("gender")
    val gender: String,
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
