package com.example.gymrat

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.gymrat.Models.AuthManager
import com.example.gymrat.Models.ProfileResponse
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentHomiesBinding
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomiesFragment : Fragment() {

    private lateinit var binding: FragmentHomiesBinding
    private lateinit var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    val authToken = AuthManager.instance.authToken
    private val user_id = AuthManager.instance.userid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomiesBinding.inflate(inflater,container,false)

        val userId = user_id
        val call = RetrofitClient.instance.getUserProfile(userId!!)
        call.enqueue(object : Callback<ProfileResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful) {
                    val profileResponse = response.body()
                    val nickname = profileResponse?.profile?.nickname
                    binding.user.text = "Welcome $nickname"
                } else {
                    Toast.makeText(context, "Failed to fetch user profile", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                // Handle failure
            }
        })
        binding.chestButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_chest1Fragment)
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_backFragment)
        }
        binding.shouldButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_shoulderFragment)
        }
        binding.tricepsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_tricepsFragment)
        }
        binding.bicepsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_bicepsFragment)
        }
        binding.legsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_legsFragment)
        }
        binding.sariSari.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_proteinFragment)
        }
        binding.createProgram.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_optionExcerFragment)
        }


        binding.settings.setOnClickListener {
            binding.navigationView.visibility = View.VISIBLE
            binding.toolbar.visibility = View.VISIBLE
        }

        binding.toolbar.setOnClickListener {
            binding.navigationView.visibility = View.INVISIBLE
            binding.toolbar.visibility = View.INVISIBLE
        }

        if (savedInstanceState == null) {
            binding.navigationView.setCheckedItem(R.id.nav_home)
        }

        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    binding.navigationView.visibility = View.INVISIBLE
                    binding.toolbar.visibility = View.INVISIBLE
                }
                R.id.nav_change -> {
                    findNavController().navigate(R.id.action_homiesFragment_to_changeFragment)
                }
                R.id.nav_programs -> {
                }
                R.id.nav_about -> {
                    findNavController().navigate(R.id.action_homiesFragment_to_aboutFragment)
                }
                R.id.nav_logout -> {
                    findNavController().navigate(R.id.action_homiesFragment_to_loginFragment2)
                }
            }
            true
        }

        return binding.root
    }

}