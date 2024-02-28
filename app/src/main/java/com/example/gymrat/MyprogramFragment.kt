package com.example.gymrat

import ProgramExercisesAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymrat.Models.AuthManager
import com.example.gymrat.Models.ProgramExercisesResponse
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentMyprogramBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyprogramFragment : Fragment() {

    private lateinit var binding: FragmentMyprogramBinding
    private lateinit var programExercisesAdapter: ProgramExercisesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyprogramBinding.inflate(inflater, container, false)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize and set up the adapter with a mutable list
        programExercisesAdapter = ProgramExercisesAdapter(mutableListOf())
        recyclerView.adapter = programExercisesAdapter

        // Fetch program exercises data
        fetchProgramExercisesData()

        return binding.root
    }

    private fun fetchProgramExercisesData() {
        val programId: Int? = AuthManager.instance.programid
        Toast.makeText(
            requireContext(),
            "$programId",
            Toast.LENGTH_SHORT
        ).show()
        val call = RetrofitClient.instance.getProgramExercises(programId!!)
        Log.d("MyProgramFragment", "Request URL: ${call.request().url}")

        call.enqueue(object : Callback<ProgramExercisesResponse> {
            override fun onResponse(
                call: Call<ProgramExercisesResponse>,
                response: Response<ProgramExercisesResponse>
            ) {
                if (response.isSuccessful) {
                    val programExercisesList = response.body()?.exercises ?: emptyList()
                    Log.d(
                        "MyProgramFragment",
                        "Fetched ${programExercisesList.size} program exercises"
                    )
                    programExercisesAdapter.setData(programExercisesList)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Failed to fetch program exercises",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

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
}