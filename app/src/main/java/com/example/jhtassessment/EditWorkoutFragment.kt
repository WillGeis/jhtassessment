package com.example.jhtassessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.jhtassessment.databinding.FragmentEditWorkoutBinding
import java.io.File

/**
 * Allows for direct editing of exercises from the .json put into the recyclerview
 */
class EditWorkoutFragment : Fragment() {

    private var _binding: FragmentEditWorkoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var exerciseId: String

    /**
     * On create view builder
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * When created adds the bindings for text boxes and buttons
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            binding.editName.setText(it.getString("name"))
            binding.editEquipment.setText(it.getString("equipment"))
            binding.editDuration.setText(it.getInt("duration").toString())
            binding.editDifficulty.setText(it.getString("difficulty"))
            exerciseId = it.getString("id") ?: ""
        }

        binding.saveButton.setOnClickListener {
            val updatedExercise = Exercise(
                name = binding.editName.text.toString(),
                id = exerciseId,
                equipment = binding.editEquipment.text.toString(),
                duration = binding.editDuration.text.toString().toIntOrNull(),
                difficulty = binding.editDifficulty.text.toString()
            )
            saveExercise(updatedExercise)
            Toast.makeText(requireContext(), "Workout updated!", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_EditFragment_to_SecondFragment)
        }
    }

    /**
     * places new object values stored in updated exercises data type into the json array
     */
    private fun saveExercise(updated: Exercise) {
        val file = File(requireContext().filesDir, "workouts.json")
        val jsonString = file.bufferedReader().use { it.readText() }
        val cleanedJson = jsonString.replace(Regex(",\\s*([}\\]])"), "$1")
        val jsonArray = org.json.JSONArray(cleanedJson)
        var found = false
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            if (obj.optString("id") == updated.id) {
                obj.put("name", updated.name)
                obj.put("equipment", updated.equipment)
                obj.put("duration", updated.duration)
                obj.put("difficulty", updated.difficulty)
                found = true
                break
            }
        }
        if (!found) { // added new workout adding for extra functionality (newObj vs obj)
            val newObj = org.json.JSONObject()
            newObj.put("name", updated.name)
            newObj.put("id", updated.id)
            newObj.put("equipment", updated.equipment)
            newObj.put("duration", updated.duration)
            newObj.put("difficulty", updated.difficulty)
            jsonArray.put(newObj)
        }
        file.writeText(jsonArray.toString())
    }

    /**
     * Destroy view
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
