package com.example.burae.models

data class BasketResultData (
    val id: Long,
    val products: List<BasketResultProduct>,
    val total: Long,
    val discountedTotal: Long,
    val userId: Long,
    val totalProducts: Long,
    val totalQuantity: Long
)

data class BasketResultProduct (
    val id: Long,
    val title: String,
    val price: Long,
    val quantity: Long,
    val total: Long,
    val discountPercentage: Double,
    val discountedPrice: Long
)
