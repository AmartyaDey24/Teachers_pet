package com.example.teacherspet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TeacherInfo extends AppCompatActivity {

    private TextView nameT,emailT,deptT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        nameT = findViewById(R.id.NameT);
        emailT = findViewById(R.id.tEmail);
        deptT = findViewById(R.id.deptT);
    }
}