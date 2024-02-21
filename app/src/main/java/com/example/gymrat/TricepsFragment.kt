package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentBackBinding
import com.example.gymrat.databinding.FragmentTricepsBinding

class TricepsFragment : Fragment() {
    private lateinit var binding: FragmentTricepsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTricepsBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_tricepsFragment_to_homiesFragment)
        }
        return binding.root
    }
}