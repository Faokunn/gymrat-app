package com.example.gymrat.StartAndHome

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.gymrat.Models.AuthManager
import com.example.gymrat.Models.CaloriesResponse
import com.example.gymrat.Models.LoginResponse
import com.example.gymrat.Models.ProfileResponse
import com.example.gymrat.Models.ProgramResponse
import com.example.gymrat.R
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentHomiesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomiesFragment : Fragment() {

    private lateinit var binding: FragmentHomiesBinding
    private lateinit var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    private val user_id = AuthManager.instance.userid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomiesBinding.inflate(inflater,container,false)
        val user_id = AuthManager.instance.userid
        val user_id2 = AuthManager.instance.userid
        val user_id3 = AuthManager.instance.userid
        val call = RetrofitClient.instance.getUserProfile(user_id!!)
        val call2 = RetrofitClient.instance.getUserProgram(user_id2!!)
        val call3 = RetrofitClient.instance.getUserCalories(user_id3!!)
        call.enqueue(object : Callback<ProfileResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful) {
                    val profileResponse = response.body()
                    val nickname = profileResponse?.profile?.nickname
                    val goalWeight = profileResponse?.profile?.goal_weight
                    val currentWeight = profileResponse?.profile?.weight
                    binding.user.text = "Welcome $nickname!"
                    binding.goalweightInt.text = "$goalWeight"
                    binding.currentweightInt.text = "$currentWeight"
                } else {
                    Toast.makeText(context, "Failed to fetch user profile", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
            }
        })
        call2.enqueue(object : Callback<ProgramResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<ProgramResponse>, response: Response<ProgramResponse>) {
                if (response.isSuccessful) {
                    val programResponse = response.body()
                    val programTitle = programResponse?.program?.title
                    binding.calInt.text = "$programTitle"
                } else {
                    Toast.makeText(context, "Failed to fetch user program", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProgramResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        call3.enqueue(object : Callback<CaloriesResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<CaloriesResponse>, response: Response<CaloriesResponse>) {
                if (response.isSuccessful) {
                    val calorieResponse = response.body()
                    val maintenance = calorieResponse?.calories?.maintenance
                    val surplus = calorieResponse?.calories?.surplus
                    val deficit = calorieResponse?.calories?.deficit

                    binding.maintenanceInt.text = "$maintenance"
                    binding.caloriesSurplusInt.text = "$surplus"
                    binding.caloriesDeticitInt.text = "$deficit"

                    if (maintenance?.toInt() == 0 && surplus?.toInt() == 0 && deficit?.toInt() == 0) {
                        binding.maintenance.visibility = View.GONE
                        binding.maintenanceInt.visibility = View.GONE
                        binding.caloriesSurplus.visibility = View.GONE
                        binding.caloriesSurplusInt.visibility = View.GONE
                        binding.caloriesDeticit.visibility = View.GONE
                        binding.caloriesDeticitInt.visibility = View.GONE

                        binding.tapToCalculate.visibility = View.VISIBLE
                    } else {
                        binding.maintenance.visibility = View.VISIBLE
                        binding.maintenanceInt.visibility = View.VISIBLE
                        binding.caloriesSurplus.visibility = View.VISIBLE
                        binding.caloriesSurplusInt.visibility = View.VISIBLE
                        binding.caloriesDeticit.visibility = View.VISIBLE
                        binding.caloriesDeticitInt.visibility = View.VISIBLE

                        binding.tapToCalculate.visibility = View.GONE
                    }
                } else {
                    Toast.makeText(context, "Failed to fetch user calories", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CaloriesResponse>, t: Throwable) {
                // Handle failure if needed
            }
        })
        binding.Goal.setOnClickListener {
            showEditWeightsDialog(requireContext())
        }
        binding.tapToCalculate.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_calculatorFragment)
        }

        binding.calsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_myprogramFragment)
        }
        binding.chestButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_chest1Fragment)
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_backFragment)
        }
        binding.shouldButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_shoulderFragment)
        }
        binding.tricepsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_tricepsFragment)
        }
        binding.bicepsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_bicepsFragment)
        }
        binding.legsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_legsFragment)
        }
        binding.sariSari.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_proteinFragment)
        }
        binding.topExercisesButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_topExercisesFragment)
        }


        binding.settings.setOnClickListener {
            binding.navigationView.visibility = View.VISIBLE
            binding.toolbar.visibility = View.VISIBLE
        }

        binding.toolbar.setOnClickListener {
            binding.navigationView.visibility = View.INVISIBLE
            binding.toolbar.visibility = View.INVISIBLE
        }

        if (savedInstanceState == null) {
            binding.navigationView.setCheckedItem(R.id.nav_home)
        }

        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    binding.navigationView.visibility = View.INVISIBLE
                    binding.toolbar.visibility = View.INVISIBLE
                }
                R.id.nav_change -> {
                    findNavController().navigate(R.id.action_homiesFragment_to_changeFragment)
                }
                R.id.nav_sarisari -> {
                    findNavController().navigate(R.id.action_homiesFragment_to_proteinFragment)
                }
                R.id.nav_programs -> {
                    findNavController().navigate(R.id.action_homiesFragment_to_requestFragment)
                }
                R.id.nav_calculator -> {
                    findNavController().navigate(R.id.action_homiesFragment_to_calculatorFragment)
                }
                R.id.nav_about -> {
                    findNavController().navigate(R.id.action_homiesFragment_to_aboutFragment)
                }
                R.id.nav_logout -> {
                    AuthManager.instance.clearuserId()
                    findNavController().navigate(R.id.action_homiesFragment_to_loginFragment2)
                }
            }
            true
        }

        return binding.root
    }
    private fun showEditWeightsDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Edit Weights")

        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_weights, null)
        val weightEditText: EditText = dialogView.findViewById(R.id.editTextWeight)
        val goalWeightEditText: EditText = dialogView.findViewById(R.id.editTextGoalWeight)

        builder.setView(dialogView)

        builder.setPositiveButton("Save") { _, _ ->
            val weight = weightEditText.text.toString()
            val goalWeight = goalWeightEditText.text.toString()
            updateWeight(weight, goalWeight)
            refreshData()
        }

        builder.setNegativeButton("Cancel", null)

        builder.show()
    }

    private fun updateWeight(weight: String, goalWeight: String) {
        val userId = AuthManager.instance.userid
        val call = RetrofitClient.instance.updateWeight(userId!!, weight, goalWeight)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Weights updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to update weights", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun refreshData() {
        val callProfile = RetrofitClient.instance.getUserProfile(user_id!!)


        callProfile.enqueue(object : Callback<ProfileResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    val profileResponse = response.body()
                    val goalWeight = profileResponse?.profile?.goal_weight
                    val currentWeight = profileResponse?.profile?.weight
                    binding.goalweightInt.text = "$goalWeight"
                    binding.currentweightInt.text = "$currentWeight"
                } else {
                    Toast.makeText(context, "Failed to fetch user profile", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
            }
        })

    }

}