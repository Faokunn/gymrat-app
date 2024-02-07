package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentIntro1Binding

class Intro1Fragment : Fragment() {
    private lateinit var binding: FragmentIntro1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentIntro1Binding.inflate(inflater,container,false)
        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_intro1Fragment_to_intro2Fragment)
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_intro1Fragment_to_intro0Fragment)
        }
        return binding.root
    }
}