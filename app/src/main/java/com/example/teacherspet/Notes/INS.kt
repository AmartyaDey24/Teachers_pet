package com.example.teacherspet.Notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.teacherspet.R

class INS : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_i_n_s)

        val m1: TextView =findViewById(R.id.ins_m1)
        val m2: TextView =findViewById(R.id.ins_m2)

        m1.setOnClickListener {
            val intent= Intent(applicationContext,Ina_M1::class.java)
            startActivity(intent)
        }
        m2.setOnClickListener {
            val intent= Intent(applicationContext,Ins_M2::class.java)
            startActivity(intent)
        }
    }
}