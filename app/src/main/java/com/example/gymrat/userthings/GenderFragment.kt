package com.example.gymrat.userthings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.R
import com.example.gymrat.databinding.FragmentGenderBinding

class GenderFragment : Fragment() {
    private lateinit var binding: FragmentGenderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenderBinding.inflate(inflater,container,false)
        binding.maleButton.setOnClickListener {

            findNavController().navigate(R.id.action_genderFragment_to_ageFragment, Bundle().apply {
                putString("gender", "MALE")
            })
        }
        binding.femaleButton.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_ageFragment, Bundle().apply {
                putString("gender", "FEMALE")
            })
        }
        binding.rather.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_ageFragment, Bundle().apply {
                putString("gender", "RNS")
            })
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_intro2Fragment)
        }
        return binding.root
    }
}