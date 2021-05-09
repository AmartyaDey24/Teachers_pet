package com.example.teacherspet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class contactFaculty extends AppCompatActivity {

    private RecyclerView facultyRecyclerView;
    private FirebaseDatabase tDatabase;
    ArrayList<UserTeacher> userTeachers;
    TeacherAdapter teacherAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_faculty);

        facultyRecyclerView = findViewById(R.id.facultyRecyclerView);

        tDatabase = FirebaseDatabase.getInstance();
        userTeachers = new ArrayList<>();

        teacherAdapter = new TeacherAdapter(this, userTeachers);
        facultyRecyclerView.setAdapter(teacherAdapter);

        tDatabase.getReference().child("Teacher").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userTeachers.clear();
                for (DataSnapshot snapshot1t : snapshot.getChildren()){
                    UserTeacher userTeacher = snapshot1t.getValue(UserTeacher.class);
                    userTeachers.add(userTeacher);
                }
                teacherAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}