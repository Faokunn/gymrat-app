package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymrat.Models.AuthManager
import com.example.gymrat.Models.LoginResponse
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
                                if (userId != null) {
                                    AuthManager.instance.setUserid(userId)
                                }
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