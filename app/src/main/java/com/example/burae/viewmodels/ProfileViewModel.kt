package com.example.burae.viewmodels

import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.burae.models.UserUpdateData

class ProfileViewModel: ViewModel()  {
    fun onClick(mainViewModel: MainViewModel,id:Int,userUpdateData: UserUpdateData){
        //Log.d("id",id.toString())
        mainViewModel.updateUserData(id,userUpdateData)
    }
}