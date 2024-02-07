package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentChestfragmentBinding
import com.example.gymrat.databinding.FragmentHomiesBinding

class Chestfragment : Fragment() {
    private lateinit var binding: FragmentChestfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChestfragmentBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_chestfragment_to_homiesFragment)
        }
        return binding.root
    }

}