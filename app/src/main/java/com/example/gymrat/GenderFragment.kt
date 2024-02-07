package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentGenderBinding
import com.example.gymrat.databinding.FragmentIntro1Binding

class GenderFragment : Fragment() {
    private lateinit var binding: FragmentGenderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenderBinding.inflate(inflater,container,false)
        binding.maleButton.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_infoFragment2)
        }
        binding.femaleButton.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_infoFragment2)
        }
        binding.rather.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_infoFragment2)
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_intro2Fragment)
        }
        return binding.root
    }
}