package com.example.teacherspet.Notes

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.teacherspet.R

class Subjects : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)

        val ins:TextView=findViewById(R.id.INS)
        val sg:TextView=findViewById(R.id.SG)

        ins.setOnClickListener {
            val intent=Intent(applicationContext,INS::class.java)
            startActivity(intent)
        }
        sg.setOnClickListener {
            val intent=Intent(applicationContext,SG::class.java)
            startActivity(intent)
        }
    }
}