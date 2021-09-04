package com.example.composeintegration.network

import com.example.composeintegration.network.models.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("/api/?format=json&results=10")
    suspend fun getUsers(): Response<UsersResponse>
}