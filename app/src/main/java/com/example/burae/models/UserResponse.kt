package com.example.burae.models

data class UserResponse(
    val id : Int ,
    val username: String,
    val email:String,
    val firstName: String,
    val lastName: String,
    val gender:String,
    val image:String,
    val token:String
)
