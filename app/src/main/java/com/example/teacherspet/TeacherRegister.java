package com.example.teacherspet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherRegister extends AppCompatActivity {

    private FirebaseAuth tAuth;
    private FirebaseDatabase tDatabase;

    private EditText teacherName, teacherEmail;
    private Button teacherCont;
    private TextView trError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        tAuth = FirebaseAuth.getInstance();
        tDatabase = FirebaseDatabase.getInstance();

        teacherName = findViewById(R.id.teacherName);
        teacherEmail = findViewById(R.id.teacherEmail);

        trError = findViewById(R.id.trerror);

        teacherCont = findViewById(R.id.teacherCont);

        teacherCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeTeacher();
            }
        });

    }

    private void welcomeTeacher() {

        String uidT = tAuth.getCurrentUser().getUid();
        String tName = teacherName.getText().toString();
        String tEmail = teacherEmail.getText().toString();

        if (tName.isEmpty() || tEmail.isEmpty()){

            trError.setText("Fill the Details");
            trError.setVisibility(View.VISIBLE);
            //Progress Bar To Do
        }
        else {
            UserTeacher userTeacher = new UserTeacher(uidT,tName,tEmail);
            tDatabase.getReference()
                    .child("Teacher")
                    .child(uidT)
                    .setValue(userTeacher)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
//                            Progress Bar Dismiss
                            Intent welcomeTeacher = new Intent(TeacherRegister.this,welcome_teacher.class);
                            startActivity(welcomeTeacher);
                            finish();
                        }
                    });
        }
    }


}