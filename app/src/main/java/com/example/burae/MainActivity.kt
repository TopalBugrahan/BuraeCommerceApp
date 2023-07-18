package com.example.burae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.burae.di.basketDao.BasketData
import com.example.burae.models.User
import com.example.burae.models.UserResponse
import com.example.burae.viewmodels.MainViewModel
import com.example.burae.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val sessionViewModel by lazy{
        ViewModelProvider(this,defaultViewModelProviderFactory).get(SessionViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val data = sessionViewModel.getBasket()
        if(data.size==0){
            sessionViewModel.insertBasket(BasketData(0,0))
        }

    }
}