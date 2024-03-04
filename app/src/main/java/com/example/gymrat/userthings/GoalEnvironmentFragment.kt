package com.example.gymrat.userthings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.gymrat.R
import com.example.gymrat.databinding.FragmentGoalEnvironmentBinding

class GoalEnvironmentFragment : Fragment() {
    private lateinit var binding: FragmentGoalEnvironmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gender = requireArguments().getString("gender").toString()
        val age = requireArguments().getString("age").toString()

        binding = FragmentGoalEnvironmentBinding.inflate(inflater, container, false)


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.goal_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.goalSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.environment_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.environmentSpinner.adapter = adapter
        }

        binding.continueButton.setOnClickListener {
            val selectedGoal = binding.goalSpinner.selectedItem.toString()
            val selectedEnvironment = binding.environmentSpinner.selectedItem.toString()
            val weight = binding.weight.text.toString()
            val height = binding.height.text.toString()
            val goal_weight = binding.goalWeight.text.toString()

            findNavController().navigate(R.id.action_goalEnvironmentFragment_to_infoFragment, Bundle().apply {
                putString("gender", gender)
                putString("age", age)
                putString("goal", selectedGoal)
                putString("environment", selectedEnvironment)
                putString("weight", weight)
                putString("height", height)
                putString("goalWeight", goal_weight)
            })
        }

        return binding.root
    }
}