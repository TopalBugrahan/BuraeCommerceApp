package com.example.burae.di.basketDao

import javax.inject.Inject

class BasketRepository @Inject constructor(private val basketDao: BasketDao) {

   fun insertBasket(basketData: BasketData){
       basketDao.insertBasket(basketData)
   }

    fun insertProduct(product: RoomProductData){
        basketDao.insertProduct(product)
    }

    fun getBasket():List<BasketData>{
        return basketDao.getBasket()
    }

    fun getBasketWithProduct(bid:Int):BasketWithProduct{
        return basketDao.getBasketWithProduct(bid)
    }
}