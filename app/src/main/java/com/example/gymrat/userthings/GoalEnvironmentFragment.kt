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
            val weight = binding.weight.text.toString().trim()
            val height = binding.height.text.toString().trim()
            val goalWeight = binding.goalWeight.text.toString().trim()


            if (selectedGoal.isEmpty() || selectedEnvironment.isEmpty() || weight.isEmpty() || height.isEmpty() || goalWeight.isEmpty()) {
                binding.weight.error = "Field cannot be empty"
                return@setOnClickListener
            }

            // Continue with navigation if all input fields are non-empty
            findNavController().navigate(R.id.action_goalEnvironmentFragment_to_infoFragment, Bundle().apply {
                putString("gender", gender)
                putString("age", age)
                putString("goal", selectedGoal)
                putString("environment", selectedEnvironment)
                putString("weight", weight)
                putString("height", height)
                putString("goalWeight", goalWeight)
            })
        }

        return binding.root
    }
}