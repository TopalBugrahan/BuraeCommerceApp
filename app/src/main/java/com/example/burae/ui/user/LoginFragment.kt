package com.example.burae.ui.user

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.burae.R
import com.example.burae.databinding.FragmentLoginBinding
import com.example.burae.di.basketDao.BasketData
import com.example.burae.di.dao.SessionId
import com.example.burae.models.User
import com.example.burae.models.UserResponse
import com.example.burae.pref.SessionManager
import com.example.burae.viewmodels.MainViewModel
import com.example.burae.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var btnLogin: Button
    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }
    val sessionViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(SessionViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        username = binding.username
        password = binding.password
        btnLogin = binding.btnLogin

        viewModel.getLoginObserverData().observe(this, object : Observer<UserResponse> {
            override fun onChanged(value: UserResponse) {
                //Log.d("value",value.toString())
                if (value != null) {
                    val sessionId = sessionViewModel.getUser(value.id)
                    //Log.d("sessionId",sessionId.toString())
                    if (sessionId != null) {
                        viewModel.setSession(value.toString())
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    } else {
                        val sessionId = SessionId(0, value.id)
                        //sessionViewModel.insertUser(sessionId)
                        viewModel.setSession(value.toString())
                        sessionViewModel.insertBasket(BasketData(value.id,0))
                        findNavController().navigate(R.id.action_loginFragment_to_appIntroFragment)
                    }
                }
            }

        })

        btnLogin.setOnClickListener {
            val usernameText = username.text.toString()
            val passwordText = password.text.toString()
            val user = User(usernameText, passwordText)
            viewModel.userLogin(user)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}