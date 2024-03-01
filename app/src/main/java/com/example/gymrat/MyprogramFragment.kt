package com.example.gymrat

import ProgramExercisesAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymrat.Models.AuthManager
import com.example.gymrat.Models.ProgramExercisesResponse
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentMyprogramBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyprogramFragment : Fragment(), ProgramExercisesAdapter.ProgramExerciseUpdateListener {

    private lateinit var binding: FragmentMyprogramBinding
    private lateinit var programExercisesAdapter: ProgramExercisesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyprogramBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        programExercisesAdapter = ProgramExercisesAdapter(mutableListOf(), this)
        recyclerView.adapter = programExercisesAdapter

        val groupMuscles = resources.getStringArray(R.array.Group_Muscle)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, groupMuscles)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = binding.exerciseTypeSpinner
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                fetchProgramExercisesData()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.overloadButton.setOnClickListener {
            showOverloadDialog()
        }
    }

    private fun fetchProgramExercisesData() {
        val programId: Int? = AuthManager.instance.programid
        val call = RetrofitClient.instance.getProgramExercises(programId!!)
        Log.d("MyProgramFragment", "Request URL: ${call.request().url}")

        call.enqueue(object : Callback<ProgramExercisesResponse> {
            override fun onResponse(call: Call<ProgramExercisesResponse>, response: Response<ProgramExercisesResponse>) {
                if (response.isSuccessful) {
                    val programExercisesList = response.body()?.exercises ?: emptyList()
                    Log.d("MyProgramFragment", "Received ${programExercisesList.size} program exercises")

                    programExercisesList.forEach {
                        Log.d("MyProgramFragment", "GroupMuscle: ${it.GroupMuscle}")
                    }

                    val selectedExerciseType = binding.exerciseTypeSpinner.selectedItem.toString()
                    Log.d("MyProgramFragment", "Selected Exercise Type: $selectedExerciseType")

                    val filteredExercises = programExercisesList.filter { it.GroupMuscle == selectedExerciseType }
                    Log.d("MyProgramFragment", "Filtered Exercises: ${filteredExercises.size}")

                    programExercisesAdapter.setData(filteredExercises)
                } else {
                    Log.e("MyProgramFragment", "Failed to fetch program exercises. HTTP Status Code: ${response.code()}")
                    Toast.makeText(
                        requireContext(),
                        "Failed to fetch program exercises. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            // Handle network failure
            override fun onFailure(call: Call<ProgramExercisesResponse>, t: Throwable) {
                Log.e("MyProgramFragment", "Failed to fetch program exercises", t)
                Toast.makeText(
                    requireContext(),
                    "Network error. Please try again later.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    @SuppressLint("SetTextI18n")
    private fun showOverloadDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Progressive Overload")

        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL

        val checkBox1 = CheckBox(requireContext())
        checkBox1.text = "2 Weeks Already Passed"
        layout.addView(checkBox1)

        val checkBox2 = CheckBox(requireContext())
        checkBox2.text = "Can Do More than Your Reps"
        layout.addView(checkBox2)

        val checkBox3 = CheckBox(requireContext())
        checkBox3.text = "Weight Feels Lighter"
        layout.addView(checkBox3)

        builder.setView(layout)

        builder.setPositiveButton("Overload") { _, _ ->
            if (checkBox1.isChecked && checkBox2.isChecked && checkBox3.isChecked) {
                RetrofitClient.instance.progressiveOverload(AuthManager.instance.programid!!).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Toast.makeText(requireContext(), "Overload Successful!", Toast.LENGTH_SHORT).show()
                            fetchProgramExercisesData()
                        } else {
                            Log.e("OverloadError", "HTTP Status Code: ${response.code()}")
                            Log.e("OverloadError", "Error Body: ${response.errorBody()?.string()}")

                            // Display a general error message
                            Toast.makeText(requireContext(), "Failed to overload", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.e("OverloadError", "Failed to overload", t)
                        Toast.makeText(requireContext(), "Network error. Please try again later.", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(requireContext(), "All requirements must be met to Progressive Overload", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel") { _, _ ->
        }

        val dialog = builder.create()
        dialog.show()
    }
    override fun onProgramExerciseUpdated() {
        Log.d("MyProgramFragment", "onProgramExerciseUpdated called")
        fetchProgramExercisesData()
    }


}