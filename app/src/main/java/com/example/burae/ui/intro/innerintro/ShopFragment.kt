package com.example.burae.ui.intro.innerintro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.burae.R
import com.example.burae.databinding.FragmentShopBinding
import com.example.burae.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment : Fragment() {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onResume() {
        //Fragment'tan Activitye ulaşıp oradaki geri ve ileri butonlarına erişim sağladık ilk sayfa olduğundan geri tuşunu iptal edip
        //sadece ileri tuşunu gösterdik
        super.onResume()
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        val btnPrev = activity?.findViewById<RelativeLayout>(R.id.btnPrev)
        val btnNext = activity?.findViewById<RelativeLayout>(R.id.btnNext)

        btnNext?.isClickable = true
        btnNext?.visibility = View.VISIBLE
        btnPrev?.isClickable = false
        btnPrev?.visibility = View.GONE

        btnNext?.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}