package com.example.burae.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.burae.R
import com.example.burae.adapters.PagerAdapter
import com.example.burae.databinding.FragmentAppIntroBinding
import com.example.burae.databinding.FragmentSplashBinding
import com.example.burae.ui.intro.innerintro.DiversityFragment
import com.example.burae.ui.intro.innerintro.ShopFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppIntroFragment : Fragment() {
    private var _binding: FragmentAppIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppIntroBinding.inflate(inflater, container, false)
        val view = binding.root
        val pageView = binding.viewPager

        val fragmentList = arrayListOf<Fragment>(
            ShopFragment(),
            DiversityFragment()
        )

        val adapter =
            PagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        pageView.adapter = adapter
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}