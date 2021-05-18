package com.example.teacherspet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser1;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mCurrentUser1 = mAuth.getCurrentUser();

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_login();
            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacher_login();
            }
        });
    }

    public void student_login() {
        Intent intent = new Intent(this, student_login.class);
        startActivity(intent);
    }

    public void teacher_login() {
        Intent intent = new Intent(this, teacher_login.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser != null){
            sendStudentHome();
        }
        else if(mCurrentUser1 != null){
            sendTeacherHome();
        }
    }

    public void sendStudentHome(){
        Intent homeIntent = new Intent(MainActivity.this, welcome_student.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    }

    public void sendTeacherHome(){
        Intent homeIntent = new Intent(MainActivity.this, welcome_teacher.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    }
}