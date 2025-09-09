package com.example.jhtassessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.jhtassessment.databinding.FragmentSecondBinding

/**
 * Main Scrolling Page //TODO add scaling (add to json)
 */
class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    /**
     * Create view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Buttons and recyclerview behaviour handler
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener { // Button
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        val exercises = parseExercisesJson("workouts.json") // Call .json Loader

        val adapter = ExercisesAdapter(exercises).apply { // RecyclerView w/ listener for editing
            onItemClick = { exercise ->
                openEditWorkoutFragment(exercise)
            }
        }

        // Binders <-- Scalable
        binding.exercisesList.layoutManager = LinearLayoutManager(requireContext())
        binding.exercisesList.adapter = adapter
    }

    /**
     * .json loader to read as text as .json provided is messy and has trailing commas etc
     */
    private fun loadJsonFromAssets(fileName: String): String {
        return requireContext().assets.open(fileName).bufferedReader().use { it.readText() }
    }

    /**
     * .json parser for messy .jsons passed in with error handling and such
     */
    private fun parseExercisesJson(fileName: String): List<Exercise> {
        val jsonString = loadJsonFromAssets(fileName)
        val exercises = mutableListOf<Exercise>()

        // Removes trailing commas inside objects
        val cleanedJson = jsonString.replace(Regex(",\\s*([}\\]])"), "$1")

        val jsonArray = org.json.JSONArray(cleanedJson) // Build .json array that has been cleaned

        for (i in 0 until jsonArray.length()) { // .json iterator
            val obj = jsonArray.getJSONObject(i)
            exercises.add( // Adds Exercise data structure to the array
                Exercise(
                    name = obj.optString("name", null),
                    id = obj.optString("id", null),
                    equipment = if (obj.has("equipment")) obj.getString("equipment") else null,
                    duration = if (obj.has("duration")) obj.getInt("duration") else null,
                    difficulty = if (obj.has("difficulty")) obj.getString("difficulty") else null
                )
            )
        }
        return exercises
    }

    /**
     * Opens the workout fragment with associated strings, fills in data if missing
     */
    private fun openEditWorkoutFragment(exercise: Exercise) {
        val bundle = Bundle().apply {
            putString("name", exercise.name)
            putString("id", exercise.id)
            putString("equipment", exercise.equipment)
            putInt("duration", exercise.duration ?: 0)
            putString("difficulty", exercise.difficulty)
        }
        findNavController().navigate(R.id.action_SecondFragment_to_EditWorkoutFragment, bundle)
    }

    /**
     * Destroyer
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/**
 * Exercise data type
 */
data class Exercise(
    val name: String?,
    val id: String?,
    val equipment: String?,
    val duration: Int?,
    val difficulty: String?
)

