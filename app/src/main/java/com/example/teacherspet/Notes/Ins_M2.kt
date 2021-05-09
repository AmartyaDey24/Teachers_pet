package com.example.teacherspet.Notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherspet.Adapters.NotesAdapter
import com.example.teacherspet.R

class Ins_M2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ins__m2)


        val list= ArrayList<Int>()
        list.add(R.drawable.ins2_01)
        list.add(R.drawable.ins2_02)
        list.add(R.drawable.ins2_03)
        list.add(R.drawable.ins2_04)
        list.add(R.drawable.ins2_05)
        list.add(R.drawable.ins2_06)
        list.add(R.drawable.ins2_07)
        list.add(R.drawable.ins2_08)
        list.add(R.drawable.ins2_09)
        list.add(R.drawable.ins2_10)
        list.add(R.drawable.ins2_11)
        list.add(R.drawable.ins2_12)
        list.add(R.drawable.ins2_13)
        list.add(R.drawable.ins2_14)
        list.add(R.drawable.ins2_15)
        list.add(R.drawable.ins2_16)
        list.add(R.drawable.ins2_17)
        list.add(R.drawable.ins2_18)
        list.add(R.drawable.ins2_19)
        list.add(R.drawable.ins2_20)
        list.add(R.drawable.ins2_21)
        list.add(R.drawable.ins2_22)
        list.add(R.drawable.ins2_23)

        val recyclerView=findViewById<RecyclerView>(R.id.ins_m2recyclerview)
        recyclerView.layoutManager= LinearLayoutManager(applicationContext)
        recyclerView.adapter= NotesAdapter(list)
    }
}