package com.example.teacherspet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class verification extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private String verificationID;

    private EditText studentEnterOtp;
    private Button studentVerificationButton;
    private ProgressBar studentVerificationProgress;
    private TextView studentVerificationFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        setTitle("Verification");

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        verificationID = getIntent().getStringExtra("AuthCredentials");

        studentEnterOtp = findViewById(R.id.studentotp);
        studentVerificationButton = findViewById(R.id.studentotpbutton);
        studentVerificationProgress = findViewById(R.id.progressBar3);
        studentVerificationFeedback = findViewById(R.id.student_verification_feedback);

        studentVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String studentOtp = studentEnterOtp.getText().toString();

                if(studentOtp.isEmpty()){
                    studentVerificationFeedback.setVisibility(View.VISIBLE);
                    studentVerificationFeedback.setText("Please Enter the OTP");
                }else{

                    studentVerificationProgress.setVisibility(View.VISIBLE);
                    studentVerificationButton.setEnabled(false);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, studentOtp);
                    signInWithPhoneAuthCredential(credential);
                }

            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(verification.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                                sendUserToHome();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                studentVerificationFeedback.setVisibility(View.VISIBLE);
                                studentVerificationFeedback.setText("Invalid OTP");
                            }
                        }
                        studentVerificationProgress.setVisibility(View.INVISIBLE);
                        studentVerificationButton.setEnabled(true);
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser != null){
            sendUserToHome();
        }
    }
    public void sendUserToHome(){
        Intent homeIntent = new Intent(verification.this, welcome_student.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    }
}