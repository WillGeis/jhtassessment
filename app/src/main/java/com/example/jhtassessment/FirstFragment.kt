package com.example.jhtassessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jhtassessment.databinding.FragmentFirstBinding

/**
 * Landing Page Fragment
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    /**
     * Layout inflater initial
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Handles the main button click
     * Expandable if more buttons are needed <--- This is part of the Scalable code
     */
    fun handleButtonClick(action: String) {
        when (action) {
            "viewExercises" -> {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
    }

    /**
     * View Creator
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Button listeners
        binding.viewExercises.setOnClickListener { handleButtonClick("viewExercises") }
    }

    /**
     * View Destroyer
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}