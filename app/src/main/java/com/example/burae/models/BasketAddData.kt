package com.example.burae.models

data class BasketAddData (
    val userId: Long,
    val products: List<BasketProduct>
)

data class BasketProduct (
    val id: Long,
    val quantity: Long
)
