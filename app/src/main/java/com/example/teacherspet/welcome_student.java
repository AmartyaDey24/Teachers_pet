package com.example.teacherspet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teacherspet.Calender.CalenderOfEvents;
import com.example.teacherspet.Chats.OfficialGroup;
import com.example.teacherspet.Notes.Subjects;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class welcome_student extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    DatabaseReference reference;

    private Button studentOfficialGrp;
    private Button studentEvent;
    private Button facultyContact;
    private Button viewNotes;
    private Button studentLogout;

    private TextView studentNameView, usnView, studentEmailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_student);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        studentOfficialGrp = findViewById(R.id.studentOfficialGrp);
        studentEvent = findViewById(R.id.studentEvent);
        facultyContact = findViewById(R.id.facultyContact);
        viewNotes = findViewById(R.id.viewNotes);
        studentLogout = findViewById(R.id.studentLogout);

        studentNameView = findViewById(R.id.studentNameView);
        usnView = findViewById(R.id.usnView);
        studentEmailView = findViewById(R.id.studentEmailview);

        reference = FirebaseDatabase.getInstance().getReference().child("Student").child(mCurrentUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.child("studentName").getValue().toString();
                String usn = snapshot.child("studentUsn").getValue().toString();
                String email = snapshot.child("studentEmail").getValue().toString();

                studentNameView.setText(username);
                usnView.setText(usn);
                studentEmailView.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



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
        viewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subjectlist = new Intent(welcome_student.this, Subjects.class);
                startActivity(subjectlist);
            }
        });
        studentEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subjectlist = new Intent(welcome_student.this, CalenderOfEvents.class);
                startActivity(subjectlist);
            }
        });
        studentOfficialGrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subjectlist = new Intent(welcome_student.this, OfficialGroup.class);
                startActivity(subjectlist);
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