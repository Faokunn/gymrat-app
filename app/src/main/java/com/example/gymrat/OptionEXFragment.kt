package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentOptionEXBinding
import com.example.gymrat.databinding.FragmentShoulderBinding

class OptionEXFragment : Fragment() {
    private lateinit var binding: FragmentOptionEXBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionEXBinding.inflate(inflater,container,false)
        binding.save.setOnClickListener {
            findNavController().navigate(R.id.action_optionEXFragment_to_myprogramFragment)
        }
        return binding.root
    }
}