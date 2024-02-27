package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gymrat.databinding.FragmentMyprogramBinding


class MyprogramFragment : Fragment() {

    private lateinit var binding: FragmentMyprogramBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyprogramBinding.inflate(inflater,container,false)

        return binding.root
    }
}