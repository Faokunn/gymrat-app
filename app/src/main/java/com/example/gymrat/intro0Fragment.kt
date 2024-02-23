package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentIntro0Binding

class intro0Fragment : Fragment() {
    private lateinit var binding: FragmentIntro0Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntro0Binding.inflate(inflater,container,false)
        binding.signup.setOnClickListener {
            findNavController().navigate(R.id.action_intro0Fragment_to_intro1Fragment)
        }
        binding.signin.setOnClickListener {
            findNavController().navigate(R.id.action_intro0Fragment_to_loginFragment2)
        }
        return binding.root
    }

}