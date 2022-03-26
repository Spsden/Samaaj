package com.example.androclass.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.androclass.R
import com.example.androclass.mvvm.Contacts
import org.w3c.dom.Text

class RvAdapter(
    private val contactsList: ArrayList<Contacts>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RvAdapter.RvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {


        val rvCard = LayoutInflater.from(parent.context).inflate(R.layout.rvcard,parent,false)
        return RvViewHolder(rvCard)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {

        val currentRvCard = contactsList[position]
        holder.firstLetter.text = contactsList[position].firstName[0].toString()
        holder.fullName.text = contactsList[position].firstName + contactsList[position].secondName


    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    inner class RvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val firstLetter: TextView = itemView.findViewById<TextView>(R.id.first_letter)
        val fullName: TextView = itemView.findViewById<TextView>(R.id.full_name)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onRvClick(position)

            }
        }
        }

        interface OnItemClickListener {
            fun onRvClick(position: Int)

        }


    }


