package com.example.composeintegration.network.models

data class User(
    val name: UserName?,
    val picture: UserPicture?,
    val email: String?,
    val phone: String?,
    val location: Location?,
    val login: Login?
)
