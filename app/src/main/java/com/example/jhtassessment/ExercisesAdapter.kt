package com.example.jhtassessment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jhtassessment.databinding.ItemExerciseBinding

data class Exercise(val name: String, val description: String)

class ExercisesAdapter(private val exercises: List<Exercise>) :
    RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(val binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.binding.nameTextView.text = exercise.name
        holder.binding.descriptionTextView.text = exercise.description
    }

    override fun getItemCount() = exercises.size
}
