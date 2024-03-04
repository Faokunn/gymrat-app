package com.example.gymrat.userthings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymrat.Models.AuthManager
import com.example.gymrat.Models.DefaultResponse
import com.example.gymrat.R
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentChangeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeFragment : Fragment() {
    private lateinit var binding: FragmentChangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangeBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_changeFragment_to_homiesFragment)
        }

        binding.continueButton.setOnClickListener {
            changePassword(AuthManager.instance.authToken!!)
        }

        return binding.root
    }

    private fun changePassword(token: String) {
        val currentPassword = binding.currentPassword.text.toString()
        val newPassword = binding.newPassword.text.toString()
        val confirmNewPassword = binding.confirmPassword.text.toString()

        RetrofitClient.instance.changePassword("Bearer $token",currentPassword, newPassword, confirmNewPassword)
            .enqueue(object : Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "Password changed successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Failed to change password", Toast.LENGTH_SHORT).show()
                }
            })
    }
}