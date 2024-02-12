package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentChest1Binding

class Chest1Fragment : Fragment() {
    private lateinit var binding: FragmentChest1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChest1Binding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_chest1Fragment)
        }
        return binding.root
    }
}