package com.example.burae.viewmodels

import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.burae.adapters.MainListAdapter
import com.example.burae.interfaces.MainListSelectListener
import com.example.burae.models.Product
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel : ViewModel() {

    var data :MutableLiveData<Int>

    init {
        data= MutableLiveData()
    }

    fun setSearchViewListener(
        searchView: SearchView,
        mainView: MainViewModel,
        products: List<Product>,
        adapter: MainListAdapter
    ) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                /*val filteredList = ArrayList<Product>()
                for (i in products) {
                    if (i.title.lowercase(Locale.ROOT).contains(newText)) {
                        filteredList.add(i)
                    }
                }
                if (filteredList.isEmpty()) {
                    Log.d("yok", "Data Yok")
                } else {
                    adapter.setList(filteredList)
                }*/
                Log.d("data", newText)
                mainView.getSearchProduct(newText)

                return true
            }
        })
    }

    fun getObserverData():MutableLiveData<Int>{
        return data
    }

    fun setData(number:Int){
        data.value=number
    }

}