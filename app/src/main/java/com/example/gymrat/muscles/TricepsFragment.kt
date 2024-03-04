package com.example.gymrat.muscles

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymrat.Models.ExercisesResponse
import com.example.gymrat.R
import com.example.gymrat.RecycleView.ExerciseAdapter
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentTricepsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TricepsFragment : Fragment() {
    private lateinit var binding: FragmentTricepsBinding
    private lateinit var exerciseAdapter: ExerciseAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTricepsBinding.inflate(inflater,container,false)
        exerciseAdapter = ExerciseAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = exerciseAdapter

        val tricepMuscles = resources.getStringArray(R.array.Tricep_Muscle)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tricepMuscles)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = binding.exerciseTypeSpinner
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                fetchData()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_tricepsFragment_to_homiesFragment)
        }
        fetchData()
        return binding.root
    }
    private fun fetchData() {
        val api = RetrofitClient.instance
        val groupMuscle = "Tricep"
        api.getExercises(groupMuscle).enqueue(object : Callback<ExercisesResponse> {
            override fun onResponse(call: Call<ExercisesResponse>, response: Response<ExercisesResponse>) {
                if (response.isSuccessful) {
                    val exercisesResponse = response.body()
                    val exercises = exercisesResponse?.exercises ?: emptyList()
                    val selectedExerciseType = binding.exerciseTypeSpinner.selectedItem.toString()
                    val filteredExercises = exercises.filter { it.targetMuscle == selectedExerciseType }

                    exerciseAdapter.setData(filteredExercises)
                } else {
                    handleApiError(response.code())
                }
            }

            override fun onFailure(call: Call<ExercisesResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "API Call failed", t)
                Toast.makeText(context, "Network error. Please try again later.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleApiError(responseCode: Int) {
        Toast.makeText(context, "API Error: $responseCode", Toast.LENGTH_SHORT).show()
    }

    private fun handleFailure(t: Throwable) {
        Log.e(ContentValues.TAG, "API Call failed", t)
        Toast.makeText(context, "Network error. Please try again later.", Toast.LENGTH_SHORT).show()
    }
}