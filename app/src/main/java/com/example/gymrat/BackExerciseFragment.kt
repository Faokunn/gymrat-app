package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentBackExerciseBinding
import com.example.gymrat.databinding.FragmentChestExerciseeBinding

class BackExerciseFragment : Fragment() {
    private lateinit var binding: FragmentBackExerciseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBackExerciseBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_backExerciseFragment_to_optionExcerFragment)
        }
        binding.save.setOnClickListener {
            findNavController().navigate(R.id.action_backExerciseFragment_to_optionExcerFragment)
        }
        return binding.root
    }
}