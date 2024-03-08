import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.gymrat.Models.AuthManager
import com.example.gymrat.Models.LoginResponse
import com.example.gymrat.R
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.model.TopExercisesData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopExercisesAdapter(
    private val context: Context,
    private var topExercisesList: List<TopExercisesData>
) : RecyclerView.Adapter<TopExercisesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_topexercise, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topExercisesData = topExercisesList[position]


        holder.exerciseNameTextView.text = topExercisesData.exerciseName
        holder.groupMuscleTextView.text = topExercisesData.groupMuscle
        holder.targetMuscleTextView.text = topExercisesData.targetMuscle
        holder.environmentTextView.text = topExercisesData.environment
        holder.numberTextView.text = "#${(position + 1)}"

        Glide.with(holder.itemView.context)
            .load("https://gymrat-4acc1b203554.herokuapp.com/images/exerciseImage/${topExercisesData.image}")
            .placeholder(R.drawable.logo)
            .error(R.drawable.about)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.image)

        holder.formButton.setOnClickListener {
            val imageUrl = "https://gymrat-4acc1b203554.herokuapp.com/images/formImage/${topExercisesData.formImage}"
            showFormDialog(holder.itemView.context, topExercisesData.ProperForm, imageUrl)
        }
        holder.addButton.setOnClickListener {
            showAddDialog(holder.itemView.context, topExercisesData)
        }
    }

    private fun showFormDialog(context: Context, properForm: String, imageUrl: String?) {
        val builder = AlertDialog.Builder(context)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_exercise_form, null)

        val exerciseFormText = dialogView.findViewById<TextView>(R.id.exerciseFormText)

        val sentences = properForm.split("\\.\\s+".toRegex())
        val formattedForm = sentences.joinToString("\n")

        exerciseFormText.text = formattedForm

        val formImage = dialogView.findViewById<ImageView>(R.id.formImage)
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.logo)
            .error(R.drawable.about)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(formImage)

        builder.setView(dialogView)
            .setTitle("Exercise Form")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }
    @SuppressLint("MissingInflatedId")
    private fun showAddDialog(context: Context, exercise: TopExercisesData) {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_add_exercise, null)

        val setsEditText: EditText = dialogView.findViewById(R.id.setsEditText)
        val repsEditText: EditText = dialogView.findViewById(R.id.repsEditText)
        val weightEditText: EditText = dialogView.findViewById(R.id.weightEditText)

        builder.setView(dialogView)
        builder.setTitle("Add ${exercise.exerciseName}")
        builder.setPositiveButton("Add") { dialog, _ ->
            val sets = setsEditText.text.toString().toIntOrNull() ?: 0
            val reps = repsEditText.text.toString().toIntOrNull() ?: 0
            val weight = weightEditText.text.toString().toIntOrNull() ?: 0
            val programId = AuthManager.instance.programid
            val goal = AuthManager.instance.goal
            if (programId != null) {
                if (goal != null) {
                    addExerciseToProgram(context, exercise, sets, reps, weight, goal,programId)
                }
            }
            else{
                Toast.makeText(context, "here", Toast.LENGTH_SHORT).show()
            }

            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun addExerciseToProgram(
        context: Context,
        exercise: TopExercisesData,
        sets: Int,
        reps: Int,
        weight: Int,
        goal: String,
        programid: Int
    ) {
        val api = RetrofitClient.instance

        api.exerciseAdd(exercise.exerciseName,exercise.groupMuscle, exercise.targetMuscle, sets, reps, weight,exercise.environment,goal ,exercise.ProperForm,programid,exercise.image,exercise.formImage)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "${exercise.exerciseName} Added To Your Program", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(context, "Network error. Please try again later.", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun getItemCount(): Int {
        return topExercisesList.size
    }

    fun updateData(newTopExercisesList: List<TopExercisesData>) {
        topExercisesList = newTopExercisesList
        notifyDataSetChanged()
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseNameTextView: TextView = itemView.findViewById(R.id.exerciseNameTextView)
        val groupMuscleTextView: TextView = itemView.findViewById(R.id.groupMuscleTextView)
        val targetMuscleTextView: TextView = itemView.findViewById(R.id.targetMuscleTextView)
        val environmentTextView: TextView=itemView.findViewById(R.id.environmentTextView)
        val numberTextView: TextView=itemView.findViewById(R.id.number)
        val addButton: Button = itemView.findViewById(R.id.addButton)
        val formButton: Button = itemView.findViewById(R.id.formButton)
        val image: ImageView = itemView.findViewById(R.id.imageView)
    }
}