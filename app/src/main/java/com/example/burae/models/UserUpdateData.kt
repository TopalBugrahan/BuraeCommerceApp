package com.example.burae.models

data class UserUpdateData(
    val firstName:String,
    val lastName: String,
    val email: String,
    val phone: String,
    val address: Address,
    val height: Long,
    val weight: Double,
)
