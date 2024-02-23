package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentChestExerciseeBinding

class ChestExerciseeFragment : Fragment() {
    private lateinit var binding: FragmentChestExerciseeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChestExerciseeBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_chestExerciseeFragment_to_optionExcerFragment)
        }
        binding.save.setOnClickListener {
            findNavController().navigate(R.id.action_chestExerciseeFragment_to_optionExcerFragment)
        }
        return binding.root
    }
}