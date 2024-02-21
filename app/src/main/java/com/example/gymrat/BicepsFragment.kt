package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentBicepsBinding
import com.example.gymrat.databinding.FragmentShoulderBinding

class BicepsFragment : Fragment() {
    private lateinit var binding: FragmentBicepsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBicepsBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_bicepsFragment_to_homiesFragment)
        }
        return binding.root
    }
}