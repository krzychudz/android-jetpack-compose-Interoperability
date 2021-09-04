package com.example.composeintegration.di.repositories

import com.example.composeintegration.network.NetworkService
import com.example.composeintegration.network.models.User
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val service: NetworkService
) {

    suspend fun getUsers(): List<User>? {
        val userResponse = service.getUsers()

        if (!userResponse.isSuccessful) {
            throw HttpException(userResponse)
        }

        return userResponse.body()?.results
    }
}