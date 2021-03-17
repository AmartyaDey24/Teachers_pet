package com.example.teacherspet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Button button1;
         Button button2;
         button1 = (Button)findViewById(R.id.button1);
         button1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 student_login();
             }
         });
         button2 = (Button)findViewById(R.id.button2);
         button2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 teacher_login();
             }
         });
    }
    public void  student_login()
    {
        Intent intent = new Intent(this, student_login.class);
        startActivity(intent);
    }
    public void teacher_login()
    {
        Intent intent = new Intent(this, teacher_login.class);
        startActivity(intent);
    }
}