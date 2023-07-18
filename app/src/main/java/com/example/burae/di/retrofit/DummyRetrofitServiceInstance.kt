package com.example.burae.di.retrofit

import com.example.burae.models.*
import retrofit2.Call
import retrofit2.http.*

interface DummyRetrofitServiceInstance {

    @POST("auth/login")
    fun login(@Body user:User):Call<UserResponse>

    @GET("products")
    fun getAllProducts():Call<ProductData>

    @GET("products/search")
    fun getSearchProduct(@Query("q") data:String):Call<ProductData>

    @GET("products/categories")
    fun getAllCategory():Call<List<String>>

    @GET("products/category/{productName}")
    fun getCategoryProduct(@Path("productName") productName:String):Call<ProductData>

    @GET("users/{userId}")
    fun getUserProfile(@Path("userId") userId:Int) : Call<UserProfile>

    @PUT("users/{userId}")
    fun updateUserData(@Path("userId") userId:Int, @Body newUserData:UserUpdateData):Call<UserProfile>
}