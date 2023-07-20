package com.example.burae.ui.homes.bottomNavs

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.burae.MainActivity
import com.example.burae.R
import com.example.burae.databinding.FragmentProfileBinding
import com.example.burae.models.Address
import com.example.burae.models.UserProfile
import com.example.burae.models.UserResponse
import com.example.burae.models.UserUpdateData
import com.example.burae.ui.user.LoginFragment
import com.example.burae.util.ParseMyString
import com.example.burae.viewmodels.MainViewModel
import com.example.burae.viewmodels.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var address: Address

    private var userResponse: UserResponse? = null

    val mainViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    val profileViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.innerContainer.visibility = View.GONE
        val data = mainViewModel.getSession()
        //Log.d("dataaa",data.toString())
        userResponse = ParseMyString().stringToUserResponse(data!!)
        Log.d("userResponse", userResponse.toString())
        if (userResponse != null) {
            mainViewModel.getUserProfile(userResponse!!.id)
        }

        mainViewModel.getUserProfileObserverData().observe(this, object : Observer<UserProfile> {
            @SuppressLint("SetTextI18n")
            override fun onChanged(value: UserProfile) {
                address = value.address
                binding.progressBar.visibility = View.GONE
                binding.innerContainer.visibility = View.VISIBLE
                binding.username.setText("${value.firstName} ${value.lastName}")
                binding.email.setText(value.email)
                binding.phoneNum.setText(value.phone)
                binding.adress.setText(value.address.address)
                binding.height.setText(value.height.toString())
                binding.weight.setText(value.weight.toString())
            }
        })

        mainViewModel.getUserUpdateObserverData().observe(
            this
        ) {
            //Log.d("updateOldumu",it.toString())
            if (it) {
                val snackbar = Snackbar.make(
                    binding.innerContainer,
                    "Başarıyla güncellendi",
                    Snackbar.LENGTH_LONG
                )
                snackbar.show()
            }
        }

        binding.btnSave.setOnClickListener {
            val fullname = binding.username.text.toString()
            val email = binding.email.text.toString()
            val height = binding.height.text.toString().toLong()
            val weight = binding.weight.text.toString().toDouble()
            val phone = binding.phoneNum.text.toString()
            val splitStr = fullname.split(" ") as MutableList<String>
            val lastName = splitStr.removeLast()
            val firstName = splitStr.joinToString(" ")
            this.address.address = binding.adress.text.toString()
            val userUpdateData =
                UserUpdateData(firstName, lastName, email, phone, this.address, height, weight)
            userResponse?.let { it1 ->
                profileViewModel.onClick(
                    mainViewModel,
                    it1.id,
                    userUpdateData
                )
            }
        }

        binding.btnLogOut.setOnClickListener {
            mainViewModel.deleteSession()
            val intent= Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}