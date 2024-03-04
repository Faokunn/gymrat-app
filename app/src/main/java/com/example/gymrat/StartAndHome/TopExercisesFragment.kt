package com.example.gymrat.StartAndHome

import TopExercisesAdapter
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymrat.Models.AuthManager
import com.example.gymrat.Models.TopExercisesResponse
import com.example.gymrat.R
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentTopExercisesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopExercisesFragment : Fragment() {
    private lateinit var binding: FragmentTopExercisesBinding
    private lateinit var topExercisesAdapter: TopExercisesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topExercisesAdapter = TopExercisesAdapter(requireContext(), emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = topExercisesAdapter
        val groupMuscles = resources.getStringArray(R.array.Group_Muscle)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, groupMuscles)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = binding.exerciseTypeSpinner
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedMuscleGroup = spinner.selectedItem.toString()
                fetchDataFromApi(selectedMuscleGroup)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        fetchDataFromApi(groupMuscles.first())
    }

    private fun fetchDataFromApi(selectedMuscleGroup: String) {
        val apiService = RetrofitClient.instance
        val goal = AuthManager.instance.goal
        val call = apiService.getTopExercisesByGoal(goal!!)

        call.enqueue(object : Callback<TopExercisesResponse> {
            override fun onResponse(call: Call<TopExercisesResponse>, response: Response<TopExercisesResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val topExercisesList = responseBody?.exercises?.get(selectedMuscleGroup) ?: emptyList()

                    topExercisesAdapter.updateData(topExercisesList)
                } else {
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TopExercisesResponse>, t: Throwable) {
                Log.e(TAG, "Network request failed", t)
            }
        })
    }


}