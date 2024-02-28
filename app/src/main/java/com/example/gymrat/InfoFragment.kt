package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymrat.Models.DefaultResponse
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentHomiesBinding
import com.example.gymrat.databinding.FragmentInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater,container,false)
        binding.continueButton.setOnClickListener {
            val nickname = binding.nickname.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val cpassword = binding.confirmPassword.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val gender = requireArguments().getString("gender").toString()
            val age = requireArguments().getString("age").toString()

            if(nickname.isEmpty()){
                binding.nickname.error = "Nickname Required"
                binding.nickname.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.password.error = "Password Required"
                binding.password.requestFocus()
                return@setOnClickListener
            }

            if(password.length !in 8..99){
                binding.password.error = "Password Must Be 8 Character Long"
                binding.password.requestFocus()
                return@setOnClickListener
            }

            if(cpassword.isEmpty()){
                binding.confirmPassword.error = "Confirm Password Required"
                binding.confirmPassword.requestFocus()
                return@setOnClickListener
            }
            if(cpassword != password){
                binding.confirmPassword.error = "Confirm Password Doesn't Match with password"
                binding.continueButton.requestFocus()
                return@setOnClickListener
            }
            if(email.isEmpty()){
                binding.email.error = "Email Required"
                binding.email.requestFocus()
                return@setOnClickListener
            }
            RetrofitClient.instance.createUser(email, password, cpassword, nickname, age,"Weight loss", gender, "$nickname's Program","Weight loss").enqueue(object :
                Callback<DefaultResponse> {
                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_infoFragment_to_loginFragment2)
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(context, "Email Already Exist", Toast.LENGTH_LONG).show()
                }
            })
        }

        return binding.root
    }

}