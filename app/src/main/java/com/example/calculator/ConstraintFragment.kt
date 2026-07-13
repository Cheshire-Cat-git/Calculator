package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.calculator.databinding.FragmentConstraintBinding
import com.example.calculator.databinding.ItemNumberBinding

class ConstraintFragment : Fragment() {
    private var _binding: FragmentConstraintBinding? = null
    private val binding get() = _binding!!

    private val buttonRowsTxt = listOf(
        listOf("1", "2", "3", "+"),
        listOf("4", "5", "6", "-"),
        listOf("7", "8", "9", "*")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConstraintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCalculatorButtons()
    }

    private fun setupCalculatorButtons() {
        buttonRowsTxt.forEach { row ->
            val rowLayout = LinearLayout(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1f
                )
                orientation = LinearLayout.HORIZONTAL
            }

            row.forEach { buttonText ->
                val itemBinding = ItemNumberBinding.inflate(
                    LayoutInflater.from(requireContext()),
                    rowLayout,
                    false
                )
                itemBinding.btnNumber.text = buttonText
                itemBinding.btnNumber.setOnClickListener {
                    binding.etResult.append(buttonText)
                }
                rowLayout.addView(itemBinding.root)
            }
            binding.numberLayout.addView(rowLayout)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}