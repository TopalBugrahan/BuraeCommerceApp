package com.example.burae.ui.intro.innerintro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.burae.R
import com.example.burae.databinding.FragmentDiversityBinding
import com.example.burae.databinding.FragmentHomeBinding
import com.example.burae.di.dao.SessionId
import com.example.burae.models.UserResponse
import com.example.burae.pref.SessionManager
import com.example.burae.util.ParseMyString
import com.example.burae.viewmodels.MainViewModel
import com.example.burae.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DiversityFragment : Fragment() {
    private var _binding: FragmentDiversityBinding? = null
    private val binding get() = _binding!!

    val sessionViewModel by lazy{
        ViewModelProvider(this,defaultViewModelProviderFactory).get(SessionViewModel::class.java)
    }

    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiversityBinding.inflate(inflater, container, false)
        val view = binding.root
        val btnGoApp=binding.btnGoApp

        btnGoApp.setOnClickListener {
            Log.d("session",viewModel.getSession().toString())
            val userData=viewModel.getSession()
            if(userData!=null){
                val userResponse=ParseMyString().stringToUserResponse(userData)
                if(userResponse!=null){
                    val sessionId=SessionId(0,userResponse.id)
                    sessionViewModel.insertUser(sessionId)
                }else{
                    Log.d("HATA dİVERSİTY","HATAA")
                }

            }
            else {
                Log.d("HATA dİVERSİTY","HATAA")
            }

            //viewModel.setSession(viewModel.getSession().toString())
            //sessionViewModel.insertUser()
            findNavController().navigate(R.id.action_appIntroFragment_to_mainFragment)
        }

        return view
    }

    override fun onResume() {
        //Fragment'tan Activitye ulaşıp oradaki geri ve ileri butonlarına erişim sağladık ilk sayfa olduğundan geri tuşunu iptal edip
        //sadece ileri tuşunu gösterdik
        super.onResume()
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        val btnPrev = activity?.findViewById<RelativeLayout>(R.id.btnPrev)
        val btnNext = activity?.findViewById<RelativeLayout>(R.id.btnNext)

        btnPrev?.isClickable = true
        btnPrev?.visibility = View.VISIBLE
        btnNext?.isClickable = false
        btnNext?.visibility = View.GONE

        btnPrev?.setOnClickListener {
            viewPager?.currentItem = 0
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}