package com.example.burae.di.retrofit

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.burae.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DummyRetrofitRepository @Inject constructor(private val dummyRetrofitServiceInstance: DummyRetrofitServiceInstance) {

    fun userLogin(user: User, mutableLiveData: MutableLiveData<UserResponse>) {
        return dummyRetrofitServiceInstance.login(user).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    mutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                mutableLiveData.postValue(null)
            }

        })
    }

    fun getAllProducts(products: MutableLiveData<ProductData>) {
        return dummyRetrofitServiceInstance.getAllProducts()
            .enqueue(object : Callback<ProductData> {
                override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                    if (response.isSuccessful) {
                        products.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ProductData>, t: Throwable) {
                    products.postValue(null)
                }
            })
    }

    fun getSearchProduct(data: String, products: MutableLiveData<ProductData>) {
        return dummyRetrofitServiceInstance.getSearchProduct(data)
            .enqueue(object : Callback<ProductData> {
                override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                    if (response.isSuccessful) {
                        products.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ProductData>, t: Throwable) {
                    products.postValue(null)
                }
            })
    }

    fun getAllCategory(products: MutableLiveData<List<String>>) {
        return dummyRetrofitServiceInstance.getAllCategory()
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("response", response.body().toString())
                        products.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {

                }
            })
    }

    fun getCategoryProduct(productName: String, products: MutableLiveData<ProductData>) {
        return dummyRetrofitServiceInstance.getCategoryProduct(productName)
            .enqueue(object : Callback<ProductData> {
                override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                    if (response.isSuccessful) {
                        Log.d("response", response.body().toString())
                        products.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ProductData>, t: Throwable) {
                    products.postValue(null)
                }
            })
    }

    fun getUserProfile(userId: Int, userProfile: MutableLiveData<UserProfile>) {
        return dummyRetrofitServiceInstance.getUserProfile(userId)
            .enqueue(object : Callback<UserProfile> {
                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                    if (response.isSuccessful) {
                        userProfile.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    userProfile.postValue(null)
                }

            })
    }

    fun updateUserData(userId:Int,userUpdateData: UserUpdateData, isUpdate:MutableLiveData<Boolean>){
        return dummyRetrofitServiceInstance.updateUserData(userId,userUpdateData).enqueue(object:Callback<UserProfile>{
            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                if(response.isSuccessful){
                    isUpdate.postValue(true)
                }
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    isUpdate.postValue(false)
            }

        })
    }

}