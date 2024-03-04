import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gymrat.Models.AuthManager
import com.example.gymrat.Models.LoginResponse
import com.example.gymrat.R
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentCalculatorBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.genderSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.active_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.physicalActivitySpinner.adapter = adapter
        }

        binding.continueButton.setOnClickListener {
            calculateTDEE()
        }

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_calculatorFragment_to_homiesFragment)
        }

        return binding.root
    }

    private fun calculateTDEE() {
        val weightStr = binding.weight.text.toString()
        val heightStr = binding.height.text.toString()
        val ageStr = binding.age.text.toString()

        if (weightStr.isNotEmpty() && heightStr.isNotEmpty() && ageStr.isNotEmpty()) {
            val weight = weightStr.toDoubleOrNull()
            val height = heightStr.toDoubleOrNull()
            val age = ageStr.toDoubleOrNull()
            val activityLevel = binding.physicalActivitySpinner.selectedItemPosition

            if (weight != null && height != null && age != null) {
                val bmr: Double = when (binding.genderSpinner.selectedItemPosition) {
                    0 -> 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age)
                    1 -> 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age)
                    else -> 0.0
                }

                val tdee: Double = when (activityLevel) {
                    0 -> bmr * 1.375
                    1 -> bmr * 1.55
                    2 -> bmr * 1.725
                    else -> 0.0
                }

                val maintenanceCalories = tdee.toInt()
                val deficitCalories = (tdee - 300).toInt()
                val surplusCalories = (tdee + 300).toInt()

                showResultsDialog(maintenanceCalories, deficitCalories, surplusCalories)
            }
        }
    }

    private fun showResultsDialog(maintenanceCalories: Int, deficitCalories: Int, surplusCalories: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("TDEE Calculation Results")
            .setMessage(
                "Maintenance Calorie: $maintenanceCalories\n" +
                        "Deficit Calorie: $deficitCalories\n" +
                        "Surplus Calorie: $surplusCalories"
            )
            .setPositiveButton("Add") { _, _ ->
                val user_id = AuthManager.instance.userid
                val maintenance = maintenanceCalories.toString()
                val surplus = surplusCalories.toString()
                val deficit = deficitCalories.toString()

                RetrofitClient.instance.updateCalories(user_id!!, maintenance, surplus, deficit)
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            if (response.isSuccessful) {
                                findNavController().navigate(R.id.action_calculatorFragment_to_homiesFragment)
                            } else {
                                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        }
                    })
            }
            .setNegativeButton("OK") { _, _ ->
            }
            .show()
    }
}
