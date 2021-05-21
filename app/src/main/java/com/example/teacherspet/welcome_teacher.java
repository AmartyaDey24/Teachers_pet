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
import com.example.teacherspet.Notes.Student_subjects;
import com.example.teacherspet.Notes.Teacher_subjects;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class welcome_teacher extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser1;

    DatabaseReference reference;

    private Button teacherOfficialGrp;
    private Button teacherEvent;
    private Button studentContact;
    private Button addNotes;
    private Button teacherLogout;

    private TextView tName,tEmail,dpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_teacher);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser1 = mAuth.getCurrentUser();

        teacherOfficialGrp = findViewById(R.id.teacherOfficialGrp);
        teacherEvent = findViewById(R.id.teacherevent);
        studentContact = findViewById(R.id.studentContact);
        addNotes = findViewById(R.id.addNotes);
        teacherLogout = findViewById(R.id.teacherLogout);

        tName = findViewById(R.id.tNameView);
        tEmail = findViewById(R.id.tEmail);
        dpt = findViewById(R.id.tDept);

        reference = FirebaseDatabase.getInstance().getReference().child("Teacher").child(mCurrentUser1.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.child("teacherName").getValue().toString();
                String email = snapshot.child("teacherEmail").getValue().toString();
                String dept = snapshot.child("dep").getValue().toString();

                tName.setText(username);
                tEmail.setText(email);
                dpt.setText(dept);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        teacherLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                sendUserToLogin1();
            }
        });

        studentContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studentContact = new Intent(welcome_teacher.this,contactStudent.class);
                startActivity(studentContact);
            }
        });

        teacherEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subjectlist = new Intent(welcome_teacher.this, CalenderOfEvents.class);
                startActivity(subjectlist);
            }
        });
        teacherOfficialGrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subjectlist = new Intent(welcome_teacher.this, OfficialGroup.class);
                startActivity(subjectlist);
            }
        });
        addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subjectlist = new Intent(welcome_teacher.this, Teacher_subjects.class);
                startActivity(subjectlist);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser1 == null){
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