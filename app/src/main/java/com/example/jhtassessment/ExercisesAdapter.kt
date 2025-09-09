package com.example.jhtassessment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jhtassessment.databinding.ItemExerciseBinding

class ExercisesAdapter(private val exercises: List<Exercise>) :
    RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(val binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]

        // Safely handle nulls and show placeholder text
        holder.binding.nameTextView.text = exercise.name ?: "Unnamed Exercise"

        // Build description from available fields
        val descriptionParts = mutableListOf<String>()
        exercise.equipment?.let { descriptionParts.add("Equipment: $it") }
        exercise.duration?.let { descriptionParts.add("Duration: $it min") }
        exercise.difficulty?.let { descriptionParts.add("Difficulty: $it") }

        holder.binding.descriptionTextView.text =
            if (descriptionParts.isNotEmpty()) descriptionParts.joinToString(", ")
            else "No details available"
    }

    override fun getItemCount() = exercises.size
}
