package com.example.teacherspet.Notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherspet.Adapters.NotesAdapter
import com.example.teacherspet.R

class Ina_M1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ina__m1)

        val list= ArrayList<Int>()
        list.add(R.drawable.ins1_01)
        list.add(R.drawable.ins1_02)
        list.add(R.drawable.ins1_03)
        list.add(R.drawable.ins1_04)
        list.add(R.drawable.ins1_05)
        list.add(R.drawable.ins1_06)
        list.add(R.drawable.ins1_07)
        list.add(R.drawable.ins1_08)
        list.add(R.drawable.ins1_09)
        list.add(R.drawable.ins1_10)
        list.add(R.drawable.ins1_11)
        list.add(R.drawable.ins1_12)
        list.add(R.drawable.ins1_13)
        list.add(R.drawable.ins1_14)
        list.add(R.drawable.ins1_15)
        list.add(R.drawable.ins1_16)
        list.add(R.drawable.ins1_17)
        list.add(R.drawable.ins1_18)
        list.add(R.drawable.ins1_19)
        list.add(R.drawable.ins1_20)
        list.add(R.drawable.ins1_21)
        list.add(R.drawable.ins1_22)
        list.add(R.drawable.ins1_23)
        list.add(R.drawable.ins1_24)

        val recyclerView=findViewById<RecyclerView>(R.id.ins_m1recyclerview)
        recyclerView.layoutManager=LinearLayoutManager(applicationContext)
        recyclerView.adapter= NotesAdapter(list)
    }


}