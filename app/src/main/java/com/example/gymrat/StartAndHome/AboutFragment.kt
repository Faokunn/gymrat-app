package com.example.gymrat.StartAndHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.R
import com.example.gymrat.databinding.FragmentAboutBinding
import com.example.gymrat.databinding.FragmentCalculatorBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFragment_to_homiesFragment)
        }
        return binding.root
    }

}