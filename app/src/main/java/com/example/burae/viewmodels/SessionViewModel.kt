package com.example.burae.viewmodels


import androidx.lifecycle.ViewModel
import com.example.burae.di.basketDao.*
import com.example.burae.di.dao.SessionId
import com.example.burae.di.dao.SessionRepositroy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(private val sessionRepositroy: SessionRepositroy, private val basketRepositroy: BasketRepository) :ViewModel(){

    fun insertUser(sessionId: SessionId){
        sessionRepositroy.insertUser(sessionId)
    }

    fun getUser(uid:Int):SessionId?{
        return sessionRepositroy.getUser(uid)
    }

    fun insertBasket(basketData: BasketData){
        basketRepositroy.insertBasket(basketData)
    }

    fun insertProduct(product: RoomProductData){
        basketRepositroy.insertProduct(product)
    }

    fun getBasketWithProduct(bid:Int): BasketWithProduct {
        return basketRepositroy.getBasketWithProduct(bid)
    }

    fun getBasket():List<BasketData>{
        return basketRepositroy.getBasket()
    }

    fun getDistinctBasketWithProduct(bid:Int):List<BasketTotalData>{
        return basketRepositroy.getDistinctBasketWithProduct(bid)
    }

    fun deleteBasketByUserId(bid:Int){
        basketRepositroy.deleteBasketByUserId(bid)
    }

    fun updateProductCount(productCount:Int,pid:Long){
        basketRepositroy.updateProductCount(productCount,pid)
    }


}