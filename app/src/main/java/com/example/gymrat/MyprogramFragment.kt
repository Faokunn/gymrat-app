package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmyrat.model.UserData
import com.example.gmyrat.view.UserAdapter
import com.example.gymrat.databinding.FragmentMyprogramBinding

class MyprogramFragment : Fragment() {

    private lateinit var binding: FragmentMyprogramBinding
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var userList:ArrayList<UserData>
    private var adapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>? = null

    private var title = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyprogramBinding.inflate(inflater,container,false)
        postToList()
        layoutManager = LinearLayoutManager(this.context)
        binding.mRecycler.layoutManager
        binding.mRecycler.adapter = adapter
        return binding.root
    }

    private fun addToList(name: String){
        title.add(name)
    }

    private fun postToList() {
        for (i in 1..10) {
            addToList("Hello")
        }
    }

}