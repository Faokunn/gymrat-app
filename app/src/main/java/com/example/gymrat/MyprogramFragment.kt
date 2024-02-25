package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmyrat.view.UserAdapter
import com.example.gymrat.databinding.FragmentMyprogramBinding
import com.example.gymrat.model.UserData

class MyprogramFragment : Fragment() {

    private lateinit var binding: FragmentMyprogramBinding
    private lateinit var newRecyclerview: RecyclerView
    private lateinit var userList:ArrayList<UserData>
    lateinit var title: Array<String>
//    private var adapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyprogramBinding.inflate(inflater,container,false)
        title = arrayOf("Taninga", "putaka")
        newRecyclerview = binding.mRecycler
        newRecyclerview.layoutManager = LinearLayoutManager(this.context)
        newRecyclerview.setHasFixedSize(true)

        userList = arrayListOf<UserData>()
        getUserdata()
        return binding.root
    }

    private fun getUserdata() {
        for(i in title.indices){

            val create = UserData(title[i])
            userList.add(create)

        }
        newRecyclerview.adapter = UserAdapter(userList)
    }
}