package com.example.burae.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.burae.R
import com.example.burae.databinding.FragmentSplashBinding
import com.example.burae.pref.SessionManager
import com.example.burae.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sessionManager: SessionManager

    val sessionViewModel by lazy{
        ViewModelProvider(this,defaultViewModelProviderFactory).get(SessionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root


        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("session",sessionManager.getIsFirstRun().toString())

            if(sessionManager.getIsFirstRun()!=null){
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }
            else{
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }

           //findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }, 3000)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}