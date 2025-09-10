package com.example.jhtassessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.jhtassessment.databinding.FragmentSecondBinding
import java.io.File

/**
 * Main Scrolling Page
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

        binding.buttonSecond.setOnClickListener { // back button
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.addExerciseFab.setOnClickListener { // add button
            val bundle = Bundle().apply {
                // empty values being filled
                putString("name", "")
                putString("id", System.currentTimeMillis().toString())
                putString("equipment", "")
                putInt("duration", 0)
                putString("difficulty", "")
            }
            findNavController().navigate(R.id.action_SecondFragment_to_EditWorkoutFragment, bundle)
        }

        val exercises = parseExercisesJson("workouts.json")

        val adapter = ExercisesAdapter(exercises).apply {
            onItemClick = { exercise ->
                openEditWorkoutFragment(exercise)
            }
        }

        binding.exercisesList.layoutManager = LinearLayoutManager(requireContext())
        binding.exercisesList.adapter = adapter
    }

    /**
     * opens asset workouts.json then copies the output into it
     */
    private fun getLocalJsonFile(): File {
        val file = File(requireContext().filesDir, "workouts.json")
        if (!file.exists()) {
            requireContext().assets.open("workouts.json").use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }
        return file
    }

    /**
     * loads the workouts.json into the file value
     */
    private fun loadJsonFromLocal(): String {
        val file = getLocalJsonFile()
        return file.bufferedReader().use { it.readText() }
    }

    /**
     * .json parser for messy .jsons passed in with error handling and such
     */
    private fun parseExercisesJson(fileName: String): List<Exercise> {
        val jsonString = loadJsonFromLocal()
        val exercises = mutableListOf<Exercise>()
        val cleanedJson = jsonString.replace(Regex(",\\s*([}\\]])"), "$1")
        val jsonArray = org.json.JSONArray(cleanedJson)
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            exercises.add(
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
