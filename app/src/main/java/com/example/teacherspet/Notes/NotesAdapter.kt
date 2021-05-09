package com.example.teacherspet.Notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherspet.R

class NotesAdapter(val list:ArrayList<Int>): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val imageholder=view.findViewById<ImageView>(R.id.imageitem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.imageslistitem, parent, false)

        return  NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.imageholder.setImageResource(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}