package com.example.teacherspet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class welcome_teacher extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private Button teacherOfficialGrp;
    private Button teacherEvent;
    private Button studentContact;
    private Button addNotes;
    private Button teacherLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_teacher);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        teacherOfficialGrp = findViewById(R.id.teacherOfficialGrp);
        teacherEvent = findViewById(R.id.teacherevent);
        studentContact = findViewById(R.id.studentContact);
        addNotes = findViewById(R.id.addNotes);
        teacherLogout = findViewById(R.id.teacherLogout);

        teacherLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                sendUserToLogin1();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser == null){
            sendUserToLogin1();
        }
    }

    private void sendUserToLogin1(){
        Intent loginIntent = new Intent(welcome_teacher.this, MainActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}