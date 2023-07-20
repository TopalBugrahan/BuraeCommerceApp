package com.example.burae.ui.homes.bottomNavs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.burae.R
import com.example.burae.adapters.BasketAdapter
import com.example.burae.databinding.FragmentBasketBinding
import com.example.burae.databinding.FragmentHomeBinding
import com.example.burae.di.basketDao.BasketTotalData
import com.example.burae.di.basketDao.RoomProductData
import com.example.burae.interfaces.BasketListSelectListener
import com.example.burae.interfaces.MainListSelectListener
import com.example.burae.models.*
import com.example.burae.util.ParseMyString
import com.example.burae.viewmodels.HomeViewModel
import com.example.burae.viewmodels.MainViewModel
import com.example.burae.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() ,BasketListSelectListener{
    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!
    lateinit var basketAdapter: BasketAdapter
    private var parseString: UserResponse?=null

    private val homeViewModel: HomeViewModel by activityViewModels()

    val mainViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    val sessionViewModel by lazy{
        ViewModelProvider(this,defaultViewModelProviderFactory).get(SessionViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        val view = binding.root

        parseString= ParseMyString().stringToUserResponse(mainViewModel.getSession()!!)
        val data =sessionViewModel.getDistinctBasketWithProduct(parseString!!.id)
        //Log.d("asdasdasdasdadas",data.toString())

        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.basketList.layoutManager=layoutManager
        basketAdapter= BasketAdapter()
        binding.basketList.adapter=basketAdapter

        basketAdapter.setListener(this)
        basketAdapter.setData(data)

        mainViewModel.getBasketResultObserverData().observe(this, object : Observer<BasketResultData> {
            override fun onChanged(value: BasketResultData) {
                homeViewModel.setData(0)
                //Log.d("value",value.toString())
                sessionViewModel.deleteBasketByUserId(value.userId.toInt())
                val newdata= listOf<BasketTotalData>()
                basketAdapter.setData(newdata)
            }
        })

        binding.btnAdd.setOnClickListener {
            val basketData= mutableListOf<BasketProduct>()
            for (item in data){
                basketData.add(BasketProduct(item.pid,item.toplam.toLong()))
            }
           mainViewModel.addBasket(BasketAddData(parseString!!.id.toLong(),basketData))
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun basketDecreaseItemClick(product: BasketTotalData) {
        //Log.d("toplam",product.toplam.toString())
        if(parseString!=null){
            sessionViewModel.updateProductCount(product.toplam,product.pid)
            val basketData=sessionViewModel.getDistinctBasketWithProduct(parseString!!.id)
            basketAdapter.setData(basketData)
            homeViewModel.setData(basketData.size)
        }

    }

    override fun basketIncreaseItemClick(product: BasketTotalData) {
        if(parseString!=null){
            val myRoomProduct =
                RoomProductData(0, product.pid,
                    parseString!!.id, product.title, product.imageUrl, 1, (product.totalPrice/product.toplam))
            sessionViewModel.insertProduct(myRoomProduct)
            val basketData=sessionViewModel.getDistinctBasketWithProduct(parseString!!.id)
            basketAdapter.setData(basketData)
        }

    }

}