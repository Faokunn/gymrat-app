package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymrat.databinding.FragmentOptionExcerBinding
import com.example.gymrat.model.UserData

class OptionExcerFragment : Fragment() {
    private lateinit var binding: FragmentOptionExcerBinding
    private lateinit var userList:ArrayList<UserData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentOptionExcerBinding.inflate(inflater,container,false)
        userList = ArrayList()

        binding.Chest.setOnClickListener {
            findNavController().navigate(R.id.action_optionExcerFragment_to_chestExerciseeFragment)
        }
        binding.Back.setOnClickListener {
            findNavController().navigate(R.id.action_optionExcerFragment_to_backExerciseFragment)
        }
        binding.save.setOnClickListener {
            findNavController().navigate(R.id.action_optionExcerFragment_to_myprogramFragment)
        }
        return binding.root
    }
}