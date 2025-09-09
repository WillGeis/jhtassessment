package com.example.jhtassessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.jhtassessment.databinding.FragmentSecondBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Button navigation
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        // Load JSON
        val exercises = parseExercisesJson("workouts.json")

        // Setup RecyclerView
        val adapter = ExercisesAdapter(exercises)
        binding.exercisesList.layoutManager = LinearLayoutManager(requireContext())
        binding.exercisesList.adapter = adapter
    }

    private fun loadJsonFromAssets(fileName: String): String {
        return requireContext().assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun parseExercisesJson(fileName: String): List<Exercise> {
        val jsonString = loadJsonFromAssets(fileName)
        val exercises = mutableListOf<Exercise>()

        // Remove trailing commas inside objects using regex
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// Put Exercise class **outside** the SecondFragment class but in the same file
data class Exercise(
    val name: String?,
    val id: String?,
    val equipment: String?,
    val duration: Int?,
    val difficulty: String?
)

