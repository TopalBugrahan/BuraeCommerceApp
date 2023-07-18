package com.example.burae.models

data class ProductData (
    val products: List<Product>,
    val total: Long,
    val skip: Long,
    val limit: Long
)
