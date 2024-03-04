package com.example.gymrat.StartAndHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gymrat.R
import com.example.gymrat.databinding.FragmentRequestBinding
import com.example.gymrat.databinding.FragmentTopExercisesBinding

class RequestFragment : Fragment() {
    private lateinit var binding: FragmentRequestBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestBinding.inflate(inflater, container, false)

        return binding.root
    }
}