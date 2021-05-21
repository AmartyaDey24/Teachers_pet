package com.example.teacherspet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TeacherInfo extends AppCompatActivity {

    private TextView nameT,emailT,deptT,course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        nameT = findViewById(R.id.EmailT);
        emailT = findViewById(R.id.tEmail);
        deptT = findViewById(R.id.deptT);
        course = findViewById(R.id.course);
    }
}