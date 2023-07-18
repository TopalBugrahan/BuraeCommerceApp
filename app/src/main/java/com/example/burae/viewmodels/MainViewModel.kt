package com.example.burae.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.burae.di.retrofit.DummyRetrofitRepository
import com.example.burae.models.*
import com.example.burae.pref.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dummyRetrofitRepository: DummyRetrofitRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private var loginData: MutableLiveData<UserResponse>
    private var allProducts: MutableLiveData<ProductData>
    private var categorys:MutableLiveData<List<String>>
    private var categoryProduct: MutableLiveData<ProductData>
    private var userProfile:MutableLiveData<UserProfile>
    private var isUserDataUpdate:MutableLiveData<Boolean>

    init {
        loginData = MutableLiveData()
        allProducts = MutableLiveData()
        categoryProduct= MutableLiveData()
        categorys= MutableLiveData()
        userProfile= MutableLiveData()
        isUserDataUpdate= MutableLiveData(false)
    }

    fun getLoginObserverData(): MutableLiveData<UserResponse> {

        return loginData
    }

    fun getCategoryProductObserverData():MutableLiveData<ProductData>{
        return categoryProduct
    }

    fun getProductsObserverData(): MutableLiveData<ProductData> {
        return allProducts
    }

    fun getCategoryObserverData(): MutableLiveData<List<String>> {
        return categorys
    }

    fun getUserProfileObserverData():MutableLiveData<UserProfile>{
        return userProfile
    }

    fun getUserUpdateObserverData():MutableLiveData<Boolean>{
        return isUserDataUpdate
    }

    fun userLogin(user: User) {
        dummyRetrofitRepository.userLogin(user, loginData)
    }

    fun setSession(string: String) {
        sessionManager.setIsFirstRun(string)
    }

    fun getSession(): String? {
        return sessionManager.getIsFirstRun()
    }

    fun getAllProducts() {
        dummyRetrofitRepository.getAllProducts(allProducts)
    }

    fun getSearchProduct(data:String) {
        dummyRetrofitRepository.getSearchProduct(data,allProducts)
    }

    fun getAllCategory(){
        dummyRetrofitRepository.getAllCategory(categorys)
    }

    fun getCategoryProduct(productName:String){
        dummyRetrofitRepository.getCategoryProduct(productName,categoryProduct)
    }

    fun getUserProfile(userId:Int){
        dummyRetrofitRepository.getUserProfile(userId,userProfile)
    }

    fun updateUserData(userId:Int,userUpdateData: UserUpdateData){
        dummyRetrofitRepository.updateUserData(userId,userUpdateData,isUserDataUpdate)
    }
}