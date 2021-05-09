package com.example.teacherspet.Notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherspet.Adapters.NotesAdapter
import com.example.teacherspet.R

class Sg_M1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sg__m1)


        val list= ArrayList<Int>()
        list.add(R.drawable.sg1_01)
        list.add(R.drawable.sg1_02)
        list.add(R.drawable.sg1_03)
        list.add(R.drawable.sg1_04)
        list.add(R.drawable.sg1_05)
        list.add(R.drawable.sg1_06)
        list.add(R.drawable.sg1_07)
        list.add(R.drawable.sg1_08)
        list.add(R.drawable.sg1_09)
        list.add(R.drawable.sg1_10)
        list.add(R.drawable.sg1_11)
        list.add(R.drawable.sg1_12)
        list.add(R.drawable.sg1_13)
        list.add(R.drawable.sg1_14)
        list.add(R.drawable.sg1_15)
        list.add(R.drawable.sg1_16)
        list.add(R.drawable.sg1_17)
        list.add(R.drawable.sg1_18)
        list.add(R.drawable.sg1_19)
        list.add(R.drawable.sg1_20)
        list.add(R.drawable.sg1_21)
        list.add(R.drawable.sg1_22)
        list.add(R.drawable.sg1_23)
        list.add(R.drawable.sg1_24)

        val recyclerView=findViewById<RecyclerView>(R.id.sg_m1recyclerview)
        recyclerView.layoutManager= LinearLayoutManager(applicationContext)
        recyclerView.adapter= NotesAdapter(list)
    }
}