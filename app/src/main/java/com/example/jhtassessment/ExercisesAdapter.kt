package com.example.jhtassessment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jhtassessment.databinding.ItemExerciseBinding

/**
 * Adapter for the exercises in the recycler view
 */
class ExercisesAdapter(private val exercises: List<Exercise>) :
    RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder>() {

    var onItemClick: ((Exercise) -> Unit)? = null

    class ExerciseViewHolder(val binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * on create view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    /**
     * Text binder
     */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]

        holder.binding.nameTextView.text = exercise.name ?: "Unknown"
        holder.binding.descriptionTextView.text =
            "Equipment: ${exercise.equipment ?: "N/A"}, Duration: ${exercise.duration ?: "N/A"} min, Difficulty: ${exercise.difficulty ?: "N/A"}" // probably not the best way to implement but I suppressed the warnings

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(exercise)
        }
    }

    override fun getItemCount(): Int = exercises.size // item counter
}

