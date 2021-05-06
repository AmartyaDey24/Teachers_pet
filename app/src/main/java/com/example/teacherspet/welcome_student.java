package com.example.teacherspet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class welcome_student extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private Button studentOfficialGrp;
    private Button studentEvent;
    private Button facultyContact;
    private Button viewNotes;
    private Button studentLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_student);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        studentOfficialGrp = findViewById(R.id.studentOfficialGrp);
        studentEvent = findViewById(R.id.studentEvent);
        facultyContact = findViewById(R.id.facultyContact);
        viewNotes = findViewById(R.id.viewNotes);
        studentLogout = findViewById(R.id.studentLogout);

        studentLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                sendUserToLogin();
            }
        });

        facultyContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facultyContact = new Intent(welcome_student.this,contactFaculty.class);
                startActivity(facultyContact);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser == null){
            sendUserToLogin();
        }
    }
    private void sendUserToLogin(){
        Intent loginIntent = new Intent(welcome_student.this, MainActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}