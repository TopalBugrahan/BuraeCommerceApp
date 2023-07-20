package com.example.burae.ui.homes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.burae.R
import com.example.burae.databinding.FragmentAppIntroBinding
import com.example.burae.databinding.FragmentMainBinding
import com.example.burae.util.ParseMyString
import com.example.burae.viewmodels.HomeViewModel
import com.example.burae.viewmodels.MainViewModel
import com.example.burae.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

   /* val homeViewModel by lazy{
        ViewModelProvider(this,defaultViewModelProviderFactory).get(HomeViewModel::class.java)
    }*/
    private val homeViewModel: HomeViewModel by activityViewModels()

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
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        val parseString= ParseMyString().stringToUserResponse(mainViewModel.getSession()!!)
        val basketData=sessionViewModel.getDistinctBasketWithProduct(parseString!!.id)
        homeViewModel.setData(basketData.size)

        setupBottomBar()


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getObserverData().observe(viewLifecycleOwner,object :Observer<Int>{
            override fun onChanged(value: Int) {
                //Log.d("sadasdasd",value.toString())

                if(value==0){
                    binding.bottomBar.dismissBadge(R.id.nav_basket)
                }
                else{
                    binding.bottomBar.showBadge(R.id.nav_basket,value)
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupBottomBar() {
        binding.bottomBar.setItemSelected(R.id.nav_home, true)
        //Log.d("sresad",binding.toString())
        binding.bottomBar.setOnItemSelectedListener { itemId ->
            val navController = childFragmentManager.primaryNavigationFragment?.findNavController()

            val options = NavOptions.Builder()
                .setPopUpTo(navController?.currentDestination?.id ?: 0, true)
                .build()

            when (itemId) {
                R.id.nav_home -> navController?.navigate(R.id.homeFragment, null, options)
                R.id.nav_category -> navController?.navigate(R.id.categoryFragment, null, options)
                R.id.nav_basket -> navController?.navigate(R.id.basketFragment, null, options)
                R.id.nav_profile -> navController?.navigate(R.id.profileFragment, null, options)
            }
        }
    }


}