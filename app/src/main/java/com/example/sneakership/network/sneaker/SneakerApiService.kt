package com.example.sneakership.network.sneaker

import retrofit2.Response
import retrofit2.http.GET

interface SneakerApiService {
    @GET("o/sneakers-list.json?alt=media&token=62c253ed-456e-4263-96f0-5d7553fb1f2b")
    suspend fun getSneakersList(): Response<List<SneakersListDtoItem>>
}