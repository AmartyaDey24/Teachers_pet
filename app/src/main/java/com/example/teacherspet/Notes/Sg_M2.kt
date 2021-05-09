package com.example.teacherspet.Notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherspet.Adapters.NotesAdapter
import com.example.teacherspet.R

class Sg_M2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sg__m2)


        val list= ArrayList<Int>()
        list.add(R.drawable.sg2_01)
        list.add(R.drawable.sg2_02)
        list.add(R.drawable.sg2_03)
        list.add(R.drawable.sg2_04)
        list.add(R.drawable.sg2_05)
        list.add(R.drawable.sg2_06)
        list.add(R.drawable.sg2_07)
        list.add(R.drawable.sg2_08)
        list.add(R.drawable.sg2_09)
        list.add(R.drawable.sg2_10)
        list.add(R.drawable.sg2_11)
        list.add(R.drawable.sg2_12)
        list.add(R.drawable.sg2_13)
        list.add(R.drawable.sg2_14)
        list.add(R.drawable.sg2_15)
        list.add(R.drawable.sg2_16)
        list.add(R.drawable.sg2_17)
        list.add(R.drawable.sg2_18)
        list.add(R.drawable.sg2_19)
        list.add(R.drawable.sg2_20)
        list.add(R.drawable.sg2_21)
        list.add(R.drawable.sg2_22)
        list.add(R.drawable.sg2_23)
        list.add(R.drawable.sg2_24)
        list.add(R.drawable.sg2_25)

        val recyclerView=findViewById<RecyclerView>(R.id.sg_m2recyclerview)
        recyclerView.layoutManager= LinearLayoutManager(applicationContext)
        recyclerView.adapter= NotesAdapter(list)
    }
}