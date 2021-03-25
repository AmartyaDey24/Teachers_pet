package com.example.teacherspet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class verification_teacher extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private String AuthCredential;

    private EditText teacherEnterOtp;
    private Button teacherVerificationButton;
    private ProgressBar teacherVerificationProgress;
    private TextView teacherVerificationFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_teacher);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        AuthCredential = getIntent().getStringExtra("AuthCredentials");

        teacherEnterOtp = findViewById(R.id.teacherotp);
        teacherVerificationButton = findViewById(R.id.teacherotpbutton);
        teacherVerificationProgress = findViewById(R.id.progressBar4);
        teacherVerificationFeedback = findViewById(R.id.teacher_verification_feedback);

        teacherVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String teacherOtp = teacherEnterOtp.getText().toString();

                if(teacherOtp.isEmpty()){
                    teacherVerificationFeedback.setVisibility(View.VISIBLE);
                    teacherVerificationFeedback.setText("Please Enter the OTP");
                }else{

                    teacherVerificationProgress.setVisibility(View.VISIBLE);
                    teacherVerificationButton.setEnabled(false);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(AuthCredential, teacherOtp);
                    signInWithPhoneAuthCredential(credential);
                }

            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(verification_teacher.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendUserToHome1();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                teacherVerificationFeedback.setVisibility(View.VISIBLE);
                                teacherVerificationFeedback.setText("Invalid OTP");
                            }
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser != null){
            sendUserToHome1();

        }
    }

    public void sendUserToHome1(){
        Intent homeIntent = new Intent(verification_teacher.this, welcome_teacher.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    }
}