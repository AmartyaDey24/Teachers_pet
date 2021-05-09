package com.example.teacherspet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.teacherspet.DataClass.UserStudent;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegister extends AppCompatActivity {

    private FirebaseAuth sAuth;
    private FirebaseDatabase sDatabase;

    private EditText studentName, studentUsn, studentEmail;
    private Button studentCont;
    private TextView srError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        sAuth = FirebaseAuth.getInstance();
        sDatabase = FirebaseDatabase.getInstance();

        studentName = findViewById(R.id.studentName);
        studentUsn = findViewById(R.id.studentUSN);
        studentEmail = findViewById(R.id.studentEmail);

        studentCont = findViewById(R.id.studentCont);

        srError = findViewById(R.id.srerror);

        studentCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeStudent();
            }
        });

    }

    private void welcomeStudent() {

        String sUid = sAuth.getCurrentUser().getUid();
        String sName = studentName.getText().toString();
        String sUsn = studentUsn.getText().toString();
        String sEmail = studentEmail.getText().toString();

        if (sName.isEmpty() || sUsn.isEmpty() || sEmail.isEmpty()){
            srError.setText("Fill the details");
            srError.setVisibility(View.VISIBLE);

//            Progress Bar to do
        }
        else {
            UserStudent userStudent = new UserStudent(sUid,sName,sUsn,sEmail);
            sDatabase.getReference()
                    .child("Student")
                    .child(sUid)
                    .setValue(userStudent)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
//                            progress Bar Dismiss
                            Intent welcomeStudent = new Intent(StudentRegister.this,welcome_student.class);
                            startActivity(welcomeStudent);
                            finish();
                        }
                    });
        }
    }

}