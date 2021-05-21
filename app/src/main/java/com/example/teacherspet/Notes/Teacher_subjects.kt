package com.example.teacherspet.Notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.teacherspet.R
import com.google.android.material.snackbar.Snackbar

class Teacher_subjects : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects_teacher)

        val ins:TextView=findViewById(R.id.INS)
        val sg:TextView=findViewById(R.id.SG)
        val upload:ImageButton=findViewById(R.id.upload_button)

        ins.setOnClickListener {
            val intent=Intent(applicationContext,INS::class.java)
            startActivity(intent)
        }
        sg.setOnClickListener {
            val intent=Intent(applicationContext,SG::class.java)
            startActivity(intent)
        }
        upload.setOnClickListener {
            Snackbar.make(it,"Notes Uploaded",Snackbar.LENGTH_SHORT).show()
        }
    }
}