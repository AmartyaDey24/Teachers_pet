package com.example.teacherspet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teacherspet.DataClass.UserTeacher;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherRegister extends AppCompatActivity {

    private FirebaseAuth tAuth;
    private FirebaseDatabase tDatabase;

    private EditText teacherName, teacherEmail,course,language;
    private Button teacherCont;
    private TextView trError;

    private AutoCompleteTextView dep;
    private ImageView depDropdown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        setTitle("Teacher Register");

        tAuth = FirebaseAuth.getInstance();
        tDatabase = FirebaseDatabase.getInstance();

        teacherName = findViewById(R.id.teacherName);
        teacherEmail = findViewById(R.id.teacherEmail);
        course = findViewById(R.id.course);
        language = findViewById(R.id.language);

        trError = findViewById(R.id.trerror);

        teacherCont = findViewById(R.id.teacherCont);

        dep = findViewById(R.id.dpt);
        depDropdown = findViewById(R.id.depDropdown);

        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,department);
        dep.setAdapter(adapter);

        depDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dep.showDropDown();
            }
        });

        teacherCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                welcomeTeacher();
            }
        });

    }

    private static String[] department = new String[]{"CSE","IT","ECE","CIVIL","MECH"};

    private void welcomeTeacher() {

        String uidT = tAuth.getCurrentUser().getUid();
        String tName = teacherName.getText().toString();
        String tEmail = teacherEmail.getText().toString();
        String dept = dep.getText().toString();
        String crs = course.getText().toString().toUpperCase();
        String lang = language.getText().toString();

        if (tName.isEmpty() || tEmail.isEmpty()){

            trError.setText("Fill the Details");
            trError.setVisibility(View.VISIBLE);
            //Progress Bar To Do
        }
        else {

            UserTeacher userTeacherM = new UserTeacher(uidT,tName,tEmail,dept,crs,lang);
            tDatabase.getReference()
                    .child("Users")
                    .child(uidT)
                    .setValue(userTeacherM);

            UserTeacher userTeacher = new UserTeacher(uidT,tName,tEmail,dept,crs,lang);
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