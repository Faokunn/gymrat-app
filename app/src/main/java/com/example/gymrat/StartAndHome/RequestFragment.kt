package com.example.gymrat.StartAndHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gymrat.Models.DefaultResponse
import com.example.gymrat.R
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentRequestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestFragment : Fragment() {
    private lateinit var binding: FragmentRequestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestBinding.inflate(inflater, container, false)
        setupSpinners()

        binding.continueButton.setOnClickListener {
            sendExerciseRequest()
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_requestFragment_to_homiesFragment)
        }

        return binding.root
    }

    private fun setupSpinners() {
        val groupMuscleOptions = resources.getStringArray(R.array.groupMuscle_options)
        val groupMuscleAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            groupMuscleOptions
        )
        groupMuscleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.groupMuscleSpinner.adapter = groupMuscleAdapter

        binding.groupMuscleSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedGroupMuscle = groupMuscleOptions[position]
                    populateTargetMuscleSpinner(selectedGroupMuscle)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        val defaultTargetMuscleOptions = resources.getStringArray(R.array.chest_options)
        val defaultTargetMuscleAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            defaultTargetMuscleOptions
        )
        defaultTargetMuscleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.targetMuscleSpinner.adapter = defaultTargetMuscleAdapter
    }

    private fun populateTargetMuscleSpinner(selectedGroupMuscle: String) {
        val targetMuscleOptions = when (selectedGroupMuscle) {
            "Chest" -> resources.getStringArray(R.array.chest_options)
            "Back" -> resources.getStringArray(R.array.back_options)
            "Bicep" -> resources.getStringArray(R.array.bicep_options)
            "Tricep" -> resources.getStringArray(R.array.tricep_options)
            "Shoulder" -> resources.getStringArray(R.array.shoulder_options)
            "Leg" -> resources.getStringArray(R.array.leg_options)
            else -> emptyArray()
        }

        val targetMuscleAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            targetMuscleOptions
        )
        targetMuscleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.targetMuscleSpinner.adapter = targetMuscleAdapter
    }

    private fun sendExerciseRequest() {
        val exerciseName = binding.exerciseName.text.toString()
        val groupMuscle = binding.groupMuscleSpinner.selectedItem.toString()
        val targetMuscle = binding.targetMuscleSpinner.selectedItem.toString()

        RetrofitClient.instance.requestExercise(exerciseName, groupMuscle, targetMuscle)
            .enqueue(object : Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "Exercise request sent successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Failed to send exercise request",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(
                        requireContext(),
                        "Network error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}