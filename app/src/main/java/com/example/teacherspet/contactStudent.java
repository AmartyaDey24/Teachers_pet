package com.example.teacherspet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.teacherspet.Adapters.StudentAdapter;
import com.example.teacherspet.DataClass.UserStudent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class contactStudent extends AppCompatActivity {

    private RecyclerView studentRecyclerView;
    private FirebaseDatabase sDatabase;
    ArrayList<UserStudent> userStudents;
    StudentAdapter studentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_student);

        studentRecyclerView = findViewById(R.id.facultyRecyclerView);

        sDatabase = FirebaseDatabase.getInstance();
        userStudents = new ArrayList<>();

        studentAdapter = new StudentAdapter(this, userStudents);
        studentRecyclerView.setAdapter(studentAdapter);

        sDatabase.getReference().child("Student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userStudents.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    UserStudent userStudent = snapshot1.getValue(UserStudent.class);
                    userStudents.add(userStudent);
                }
                studentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}