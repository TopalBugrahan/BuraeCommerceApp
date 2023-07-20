package com.example.burae.interfaces

import com.example.burae.di.basketDao.BasketTotalData

interface BasketListSelectListener {
    fun basketDecreaseItemClick(product: BasketTotalData)
    fun basketIncreaseItemClick(product: BasketTotalData)
}