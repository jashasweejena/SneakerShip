package com.example.sneakership.network.sneaker

import retrofit2.Response
import retrofit2.http.GET

interface SneakerApiService {
    @GET("o/sneakers-list.json?alt=media&token=65cb4350-b370-4211-8947-25b7eb34ff13")
    suspend fun getSneakersList(): Response<List<SneakersListDtoItem>>
}