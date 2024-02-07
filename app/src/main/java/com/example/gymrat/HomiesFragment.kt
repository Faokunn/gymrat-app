package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentHomiesBinding
import com.example.gymrat.databinding.FragmentIntro1Binding

class HomiesFragment : Fragment() {

    private lateinit var binding: FragmentHomiesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomiesBinding.inflate(inflater,container,false)
        binding.chestButton.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_chestfragment)
        }
        binding.settings.setOnClickListener {
            findNavController().navigate(R.id.action_homiesFragment_to_settingFragment)
        }
        return binding.root
    }

}