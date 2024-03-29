import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.gymrat.Models.LoginResponse
import com.example.gymrat.R
import com.example.gymrat.api.Api
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.model.ProgramExerciseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProgramExercisesAdapter(
    private var programExercises: List<ProgramExerciseData>,
    private val updateListener: ProgramExerciseUpdateListener
) : RecyclerView.Adapter<ProgramExercisesAdapter.ProgramExerciseViewHolder>() {

    class ProgramExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val targetMuscleTextView: TextView = itemView.findViewById(R.id.targetMuscleTextView)
        val exerciseNameTextView: TextView = itemView.findViewById(R.id.exerciseNameTextView)
        val setsTextView: TextView = itemView.findViewById(R.id.sets)
        val repsTextView: TextView = itemView.findViewById(R.id.reps)
        val weightTextView: TextView = itemView.findViewById(R.id.weight)
        val editButton: Button = itemView.findViewById(R.id.Pencil)
        val image: ImageView = itemView.findViewById(R.id.imageView)
        val deleteButton: Button = itemView.findViewById(R.id.Trashcan)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_program, parent, false)
        return ProgramExerciseViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProgramExerciseViewHolder, position: Int) {
        val programExercise = programExercises[position]

        holder.targetMuscleTextView.text = programExercise.targetMuscle
        holder.exerciseNameTextView.text = programExercise.exercise
        holder.setsTextView.text = "Sets: ${programExercise.sets}"
        holder.repsTextView.text = "Reps: ${programExercise.reps}"
        holder.weightTextView.text = "Weight: ${programExercise.weight} kg"

        Glide.with(holder.itemView.context)
            .load("https://gymrat-4acc1b203554.herokuapp.com/images/exerciseImage/${programExercise.image}")
            .placeholder(R.drawable.logo)
            .error(R.drawable.about)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.image)

        holder.editButton.setOnClickListener {
            showEditDialogWithData(holder.itemView.context, programExercise)
        }
        holder.deleteButton.setOnClickListener {
            showDeleteConfirmationDialog(holder.itemView.context, programExercise)
        }
        Log.d("ProgramExercisesAdapter", "Selected Exercise Type in Adapter: ${programExercise.GroupMuscle}")
    }


    private fun showEditDialogWithData(context: Context, programExercise: ProgramExerciseData) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Edit ${programExercise.exercise}")

        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_exercise, null)
        val setsEditText: EditText = dialogView.findViewById(R.id.editSets)
        val repsEditText: EditText = dialogView.findViewById(R.id.editReps)
        val weightEditText: EditText = dialogView.findViewById(R.id.editWeight)

        builder.setView(dialogView)

        builder.setPositiveButton("Save") { _, _ ->
            val newSets = setsEditText.text.toString().toIntOrNull() ?: programExercise.sets
            val newReps = repsEditText.text.toString().toIntOrNull() ?: programExercise.reps
            val newWeight = weightEditText.text.toString().toIntOrNull() ?: programExercise.weight

            updateProgramExercise(programExercise.id, newSets, newReps, newWeight)
        }

        builder.setNegativeButton("Cancel", null)

        builder.show()
    }

    private fun showDeleteConfirmationDialog(context: Context, programExercise: ProgramExerciseData) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete ${programExercise.exercise}")
        builder.setMessage("Are you sure you want to delete this exercise?")

        builder.setPositiveButton("Delete") { _, _ ->
            deleteProgramExercise(programExercise.id)
        }

        builder.setNegativeButton("Cancel", null)

        builder.show()
    }

    override fun getItemCount(): Int {
        return programExercises.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newProgramExercises: List<ProgramExerciseData>) {
        Log.d("ProgramExercisesAdapter", "setData called with ${newProgramExercises.size} items")
        programExercises = newProgramExercises.sortedBy { it.targetMuscle }
        notifyDataSetChanged()
    }

    private fun updateProgramExercise(id: Int, sets: Int, reps: Int, weight: Int) {
        RetrofitClient.instance.updateProgramExercise(id, sets, reps, weight).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {

                    updateListener.onProgramExerciseUpdated()
                } else {
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            }
        })
    }

    private fun deleteProgramExercise(id: Int) {
        RetrofitClient.instance.deleteProgramExercise(id).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    updateListener.onProgramExerciseUpdated()
                } else {

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

            }
        })
    }
    interface ProgramExerciseUpdateListener {
        fun onProgramExerciseUpdated()
    }
}
