package com.example.sneakership.ui.home.data
import com.google.gson.annotations.SerializedName


data class SneakersListDto(
    val sneakersItems: ArrayList<SneakersListDtoItem>
)

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
)

data class Media(
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("smallImageUrl")
    val smallImageUrl: String,
    @SerializedName("thumbUrl")
    val thumbUrl: String
)