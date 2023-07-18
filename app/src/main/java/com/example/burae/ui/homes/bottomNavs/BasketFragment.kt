package com.example.burae.ui.homes.bottomNavs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.burae.R
import com.example.burae.adapters.BasketAdapter
import com.example.burae.databinding.FragmentBasketBinding
import com.example.burae.databinding.FragmentHomeBinding
import com.example.burae.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!
    lateinit var basketAdapter: BasketAdapter

    val sessionViewModel by lazy{
        ViewModelProvider(this,defaultViewModelProviderFactory).get(SessionViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        val view = binding.root

        val data =sessionViewModel.getBasketWithProduct(1)
        Log.d("asdasdasdasdadas",data.products.toString())

        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.basketList.layoutManager=layoutManager
        basketAdapter= BasketAdapter()
        binding.basketList.adapter=basketAdapter

        basketAdapter.setData(data.products)


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}