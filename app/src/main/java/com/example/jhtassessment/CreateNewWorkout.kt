package com.example.jhtassessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jhtassessment.databinding.FragmentPickExercisesAdapterBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class CreateNewWorkout : Fragment() {

    private var _binding: FragmentPickExercisesAdapterBinding? = null
    private val binding get() = _binding!!

    private lateinit var exercises: List<PickExercise>
    private val selectedExercises = mutableListOf<PickExercise>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPickExercisesAdapterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load all exercises from workouts.json
        exercises = parseExercisesJson("workouts.json")

        // RecyclerView Adapter with click listener
        val adapter = PickExercisesAdapter(exercises).apply {
            onItemClick = { exercise ->
                if (!selectedExercises.contains(exercise)) {
                    selectedExercises.add(exercise)
                    saveSelectedExercises()
                    Toast.makeText(requireContext(), "${exercise.name} added", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.exercisesList.layoutManager = LinearLayoutManager(requireContext())
        binding.exercisesList.adapter = adapter

        // Previous button navigates back to FirstFragment
        binding.buttonPrevious.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun loadJsonFromAssets(fileName: String): String {
        return requireContext().assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun parseExercisesJson(fileName: String): List<PickExercise> {
        val jsonString = loadJsonFromAssets(fileName)
        val cleanedJson = jsonString.replace(Regex(",\\s*([}\\]])"), "$1")
        val jsonArray = JSONArray(cleanedJson)
        val exercises = mutableListOf<PickExercise>()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            exercises.add(
                PickExercise(
                    name = obj.optString("name", null),
                    id = obj.optString("id", null),
                    equipment = obj.optString("equipment", null),
                    duration = if (obj.has("duration")) obj.getInt("duration") else null,
                    difficulty = obj.optString("difficulty", null)
                )
            )
        }
        return exercises
    }

    private fun saveSelectedExercises() {
        val tempDir = File(requireContext().filesDir, ".temp")
        if (!tempDir.exists()) tempDir.mkdir()
        val outputFile = File(tempDir, "workout1.json")

        val jsonArray = JSONArray()
        for (exercise in selectedExercises) {
            val obj = JSONObject()
            obj.put("name", exercise.name)
            obj.put("id", exercise.id)
            obj.put("equipment", exercise.equipment)
            obj.put("duration", exercise.duration)
            obj.put("difficulty", exercise.difficulty)
            jsonArray.put(obj)
        }

        outputFile.writeText(jsonArray.toString(4))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class PickExercise(
    val name: String?,
    val id: String?,
    val equipment: String?,
    val duration: Int?,
    val difficulty: String?
)
