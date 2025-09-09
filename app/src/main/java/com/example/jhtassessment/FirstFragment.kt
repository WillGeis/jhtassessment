package com.example.jhtassessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jhtassessment.databinding.FragmentFirstBinding

/**
 * Fragment for Landing Page
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Button Handler
        fun handleButtonClick(action: String) {
            when (action) {
                "viewExercises" -> {
                    // TODO: Navigate or perform action for viewing exercises
                }
                "createWorkout" -> {
                    // TODO: Navigate or perform action for creating a workout
                }
                "viewWorkouts" -> {
                    // TODO: Navigate or perform action for viewing workouts
                }
            }
        }

        // Set button listeners
        binding.viewExcercises.setOnClickListener { handleButtonClick("viewExercises") }
        binding.createButton.setOnClickListener { handleButtonClick("createWorkout") }
        binding.viewWorkouts.setOnClickListener { handleButtonClick("viewWorkouts") }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}