package com.example.gymrat.StartAndHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.R
import com.example.gymrat.databinding.FragmentIntro2Binding

class Intro2Fragment : Fragment() {
    private lateinit var binding: FragmentIntro2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntro2Binding.inflate(inflater,container,false)
        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_intro2Fragment_to_genderFragment)
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_intro2Fragment_to_intro1Fragment)
        }
        return binding.root
    }
}