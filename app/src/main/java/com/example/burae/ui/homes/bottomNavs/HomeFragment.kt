package com.example.burae.ui.homes.bottomNavs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.burae.adapters.MainListAdapter
import com.example.burae.databinding.FragmentHomeBinding
import com.example.burae.interfaces.MainListSelectListener
import com.example.burae.models.Product
import com.example.burae.viewmodels.HomeViewModel
import com.example.burae.viewmodels.MainViewModel
import com.example.burae.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.burae.di.basketDao.RoomProductData
import com.example.burae.models.ProductData

@AndroidEntryPoint
class HomeFragment : Fragment(), MainListSelectListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var mainListAdapter: MainListAdapter
    lateinit var myProductData: RoomProductData
    private var count = 0

    val mainViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    val sessionViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(SessionViewModel::class.java)
    }

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val spanCount = 2
        val layoutManager = GridLayoutManager(context, spanCount)
        binding.prodoctList.setHasFixedSize(true)
        binding.prodoctList.layoutManager = layoutManager
        mainListAdapter = MainListAdapter()
        binding.prodoctList.adapter = mainListAdapter

        mainViewModel.getProductsObserverData().observe(this, object : Observer<ProductData> {
            override fun onChanged(value: ProductData) {
                //Log.d("value",value.toString())
                if (value != null) {
                    mainListAdapter.setList(value.products)
                    viewModel.setSearchViewListener(
                        binding.searchBar,
                        mainViewModel,
                        value.products,
                        mainListAdapter
                    )
                }
            }
        })
        mainViewModel.getAllProducts()

        mainListAdapter.setListener(this)



        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(product: Product) {
        this.count++
        val myRoomProduct =
            RoomProductData(0, product.id,1, product.title, product.images.get(0), 1, product.price)
        sessionViewModel.insertProduct(myRoomProduct)
        viewModel.setData(count)
    }

}