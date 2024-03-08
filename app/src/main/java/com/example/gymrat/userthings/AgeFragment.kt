package com.example.gymrat.userthings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymrat.R
import com.example.gymrat.databinding.FragmentAgeBinding

class AgeFragment : Fragment() {

    private lateinit var binding: FragmentAgeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gender = requireArguments().getString("gender").toString()

        binding = FragmentAgeBinding.inflate(inflater, container, false)

        binding.continueButton.setOnClickListener {
            val ageInput = binding.ageEditText.text.toString().trim()

            if (ageInput.isNotBlank()) {
                val age = ageInput.toIntOrNull()

                if (age != null && age in 12..60) {
                    findNavController().navigate(R.id.action_ageFragment_to_goalEnvironmentFragment, Bundle().apply {
                        putString("gender", gender)
                        putInt("age", age)
                    })
                }
                else{
                    Toast.makeText(requireContext(), "Please enter a valid age between 12 and 60", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter your age", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}