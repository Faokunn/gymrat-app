package com.example.gmyrat.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.gmyrat.model.UserData
import com.example.gmyrat.view.UserAdapter.UserViewHolder
import com.example.gymrat.R
import com.example.gymrat.databinding.FragmentOptionExcerBinding

class UserAdapter(private var title: List<String>):RecyclerView.Adapter<UserViewHolder>()
{

  inner class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
      val name: TextView = itemView.findViewById(R.id.button)
      init {
          itemView.setOnClickListener{
              val position: Int = adapterPosition
              var list = listOf<String>("Chest", "Back", "Shoulder", "Triceps", "Biceps", "Legs")
              Toast.makeText(itemView.context, "lord" + list, Toast.LENGTH_SHORT).show()
          }
      }
  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val v  = inflater.inflate(R.layout.layout,parent,false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       holder.name.text = title[position]
    }

    override fun getItemCount(): Int {
      return title.size
    }
}