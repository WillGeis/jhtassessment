package com.example.jhtassessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.jhtassessment.databinding.FragmentEditWorkoutBinding

/**
 * Allows for direct editing of exercises from the .json put into the recyclerview
 */
class EditWorkoutFragment : Fragment() { // TODO: edits actually need to work

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

        // Retrieve exercise data from recycler
        arguments?.let {
            binding.editName.setText(it.getString("name"))
            binding.editEquipment.setText(it.getString("equipment"))
            binding.editDuration.setText(it.getInt("duration").toString())
            binding.editDifficulty.setText(it.getString("difficulty"))
            exerciseId = it.getString("id") ?: ""
        }

        // Button area, scalable
        binding.saveButton.setOnClickListener {
            Toast.makeText(requireContext(), "Workout updated!", Toast.LENGTH_SHORT).show()
            requireActivity().onBackPressed() // probably should be set up in nav graph, but this works
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_EditFragment_to_SecondFragment)
        }
    }

    /**
     * Destroy view
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
