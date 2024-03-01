package com.example.gymrat

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymrat.Models.AuthManager
import com.example.gymrat.Models.LoginResponse
import com.example.gymrat.Models.ProgramResponse
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentInfoBinding
import com.example.gymrat.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_intro1Fragment)
        }
        binding.signInButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            GlobalScope.launch(Dispatchers.Main) {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.instance.userLogin(email, password).enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            if (response.isSuccessful) {
                                val token = response.body()?.token
                                val userId = response.body()?.data?.id
                                val call2 = RetrofitClient.instance.getUserProgram(userId!!)
                                call2.enqueue(object : Callback<ProgramResponse> {
                                    @SuppressLint("SetTextI18n")
                                    override fun onResponse(call: Call<ProgramResponse>, response: Response<ProgramResponse>) {
                                        if (response.isSuccessful) {
                                            val programResponse = response.body()
                                            val programId = programResponse?.program?.id
                                            val programGoal = programResponse?.program?.goal
                                            Toast.makeText(context, "$programId", Toast.LENGTH_SHORT).show()
                                            if (programId != null) {
                                                AuthManager.instance.setProgramId(programId)
                                            }
                                            if (programGoal != null) {
                                                AuthManager.instance.setGoal(programGoal)
                                            }
                                        } else {
                                            Toast.makeText(context, "Failed to fetch user program", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    override fun onFailure(call: Call<ProgramResponse>, t: Throwable) {
                                    }
                                })

                                AuthManager.instance.setUserid(userId)
                                Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
                                if (token != null) {
                                    AuthManager.instance.setAuthToken(token)
                                }
                                val bundle = Bundle().apply {
                                    putString("token", token)
                                }
                                findNavController().navigate(R.id.action_loginFragment2_to_homiesFragment)
                            } else {
                                Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            // Handle failure, e.g., show an error message
                            Toast.makeText(context, "Login failed. Please try again.", Toast.LENGTH_LONG).show()
                        }
                    })
                }
            }
        }
        return binding.root
    }

}