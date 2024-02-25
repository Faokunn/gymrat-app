package com.example.gmyrat.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.gymrat.R
import com.example.gymrat.model.UserData


class UserAdapter(private var title: ArrayList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.layout, parent, false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       holder.name.text = title[position].toString()
    }

    override fun getItemCount(): Int {
      return title.size
    }

    class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name: Button = itemView.findViewById(R.id.abcd)
//      init {
//          itemView.setOnClickListener{
//              val position: Int = adapterPosition
//              Toast.makeText(itemView.context, "lord" + list, Toast.LENGTH_SHORT).show()
//          }
//      }
    }

}