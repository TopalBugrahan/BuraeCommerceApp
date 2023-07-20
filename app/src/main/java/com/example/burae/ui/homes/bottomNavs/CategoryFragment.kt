package com.example.burae.ui.homes.bottomNavs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.burae.R
import com.example.burae.adapters.CategoryExpandableListAdapter
import com.example.burae.databinding.FragmentBasketBinding
import com.example.burae.databinding.FragmentCategoryBinding
import com.example.burae.di.basketDao.RoomProductData
import com.example.burae.interfaces.MainListSelectListener
import com.example.burae.models.Category
import com.example.burae.models.Product
import com.example.burae.models.ProductData
import com.example.burae.util.ParseMyString
import com.example.burae.viewmodels.CategoryViewModel
import com.example.burae.viewmodels.HomeViewModel
import com.example.burae.viewmodels.MainViewModel
import com.example.burae.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class CategoryFragment : Fragment(),MainListSelectListener {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListAdapter: CategoryExpandableListAdapter
    private var categoryList: MutableList<Category> = mutableListOf()
    private var categoryName:String="null"
    private val viewModel: HomeViewModel by activityViewModels()
    val sessionViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(SessionViewModel::class.java)
    }

    val mainViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        //homeViewModel.fetchData(categoryList)
        mainViewModel.getCategoryProductObserverData().observe(this,object:Observer<ProductData>{
            override fun onChanged(value: ProductData) {
                //Burayı sor
                //Log.d("asdasdasdasdasd",value.toString())
                val productsMap = mapOf(categoryName to value.products)
                expandableListAdapter.updateProductsMap(productsMap)
            }

        })
        loadCategories()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadCategories() {
        // API'den kategori isimlerini alın
        mainViewModel.getAllCategory()
        mainViewModel.getCategoryObserverData()
            .observe(viewLifecycleOwner, object : Observer<List<String>> {
                override fun onChanged(value: List<String>) {
                    //Log.d("value",value.toString())
                    // Kategori isimlerini Category nesnelerine dönüştürün
                    val categoryList = value.map {
                        Category(it)
                    }
                    //Log.d("categoryList",categoryList.toString())
                    // ExpandableListView'a kategorileri yükleyin
                    expandableListView = binding.expandableListView

                    expandableListAdapter =
                        CategoryExpandableListAdapter(requireContext(), categoryList,this@CategoryFragment)
                    expandableListView.setAdapter(expandableListAdapter)


                    expandableListView.setOnGroupExpandListener { groupPosition ->
                        for (i in 0 until expandableListView.expandableListAdapter.groupCount) {
                            if (i != groupPosition && expandableListView.isGroupExpanded(i)) {
                                expandableListView.collapseGroup(i)
                            }
                        }
                        expandableListView.post {
                            expandableListView.smoothScrollToPosition(groupPosition)
                        }
                        val categoryName = categoryList[groupPosition].categoryName
                        //Log.d("bura", "bura")
                        loadProductsByCategory(categoryName)

                        // Grup açıldığında yapılacak işlemi burada yapabilirsiniz.
                        // Örnek: Seçilen gruptaki verileri yükleme, başka bir işlem yapma vb.
                    }
                }
            })
    }


    private fun loadProductsByCategory(categoryName: String) {
        mainViewModel.getCategoryProduct(categoryName)
        this.categoryName=categoryName
    }

    override fun onItemClick(product: Product) {
        //Log.d("asdasd","asdasdas")
        val parseString= ParseMyString().stringToUserResponse(mainViewModel.getSession()!!)
        if(parseString!=null){
            val myRoomProduct =
                RoomProductData(0, product.id,parseString.id, product.title, product.images.get(0), 1, product.price)
            sessionViewModel.insertProduct(myRoomProduct)
            val basketData=sessionViewModel.getDistinctBasketWithProduct(parseString.id)
            viewModel.setData(basketData.size)
        }
    }


}