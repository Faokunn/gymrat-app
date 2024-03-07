package com.example.gymrat.StartAndHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.R
import com.example.gymrat.databinding.FragmentAboutBinding
import com.example.gymrat.databinding.FragmentProteinBinding

class ProteinFragment : Fragment() {
    private lateinit var binding: FragmentProteinBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProteinBinding.inflate(inflater, container, false)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_proteinFragment_to_homiesFragment)
        }
        return binding.root
    }

}