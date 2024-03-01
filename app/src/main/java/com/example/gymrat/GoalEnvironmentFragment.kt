package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentAgeBinding
import com.example.gymrat.databinding.FragmentGoalEnvironmentBinding

class GoalEnvironmentFragment : Fragment() {
    private lateinit var binding: FragmentGoalEnvironmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gender = requireArguments().getString("gender").toString()

        binding = FragmentGoalEnvironmentBinding.inflate(inflater, container, false)


        return binding.root
    }
}