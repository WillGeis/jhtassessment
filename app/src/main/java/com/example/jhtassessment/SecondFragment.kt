package com.example.jhtassessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.jhtassessment.databinding.FragmentSecondBinding
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

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
        val jsonString = loadJsonFromAssets("workouts.json")
        val exercises = parseExercisesJson(jsonString)

        // Setup RecyclerView
        val adapter = ExercisesAdapter(exercises)
        binding.excercisesList.layoutManager = LinearLayoutManager(requireContext())
        binding.excercisesList.adapter = adapter
    }

    private fun loadJsonFromAssets(fileName: String): String {
        return requireContext().assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun parseExercisesJson(jsonString: String): List<Exercise> {
        val gson = Gson()
        val type = object : TypeToken<List<Exercise>>() {}.type
        return gson.fromJson(jsonString, type)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
