package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentShoulderBinding
import com.example.gymrat.databinding.FragmentTricepsBinding

class ShoulderFragment : Fragment() {
    private lateinit var binding: FragmentShoulderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoulderBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_shoulderFragment_to_homiesFragment)
        }
        return binding.root
    }
}