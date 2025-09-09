package com.example.jhtassessment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jhtassessment.databinding.ItemPickExerciseBinding

class PickExercisesAdapter(private val exercises: List<PickExercise>) :
    RecyclerView.Adapter<PickExercisesAdapter.PickExerciseViewHolder>() {

    var onItemClick: ((PickExercise) -> Unit)? = null

    class PickExerciseViewHolder(val binding: ItemPickExerciseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickExerciseViewHolder {
        val binding = ItemPickExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PickExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PickExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.binding.nameText.text = exercise.name ?: "Unknown"
        holder.binding.detailsText.text =
            "Equipment: ${exercise.equipment ?: "N/A"}, Duration: ${exercise.duration ?: "N/A"} min, Difficulty: ${exercise.difficulty ?: "N/A"}"

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(exercise)
        }
    }

    override fun getItemCount(): Int = exercises.size
}
