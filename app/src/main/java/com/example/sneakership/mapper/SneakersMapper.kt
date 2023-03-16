package com.example.sneakership.feature.home.data

import com.example.sneakership.data.local.sneaker.SneakerUiItem
import com.example.sneakership.feature.home.data.api.SneakersListDtoItem

fun SneakersListDtoItem.toUiItem(): SneakerUiItem =
    SneakerUiItem(id, brand, colors, gender, media, name, releaseDate, retailPrice, sizes, styleId)

fun List<SneakersListDtoItem>.toUiList(): List<SneakerUiItem> =
    map { SneakerUiItem(it.id, it.brand, it.colors, it.gender, it.media, it.name, it.releaseDate, it.retailPrice, it.sizes, it.styleId) }