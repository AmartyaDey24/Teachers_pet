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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class student_login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private EditText student_countryCode;
    private EditText student_phone;
    private EditText student_name;
    private EditText student_usn;
    private EditText student_email;

    private Button student_loginButton;
    private ProgressBar student_progress;

    private TextView student_feedback;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks studentCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        setTitle("Student Login");

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        student_countryCode = findViewById(R.id.countrycode);
        student_phone = findViewById(R.id.student_mob);
        student_name = findViewById(R.id.student_name);
        student_usn = findViewById(R.id.student_usn);
        student_email = findViewById(R.id.student_email);
        student_loginButton = findViewById(R.id.student_login_button);
        student_progress = findViewById(R.id.progressBar);
        student_feedback = findViewById(R.id.feedback1);

        student_loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentCountryCode = student_countryCode.getText().toString();
                String studentPhone = student_phone.getText().toString();
                String studentName = student_name.getText().toString();
                String studentUSN = student_usn.getText().toString();
                String studentEmail = student_email.getText().toString();
                String studentCompletePhone = studentCountryCode + studentPhone;

                if (studentCountryCode.isEmpty() || studentPhone.isEmpty() || studentName.isEmpty() || studentUSN.isEmpty() || studentEmail.isEmpty()) {
                    student_feedback.setText("Please fill in the complete form");
                    student_feedback.setVisibility(View.VISIBLE);
                } else {
                    student_progress.setVisibility(View.VISIBLE);
                    student_loginButton.setEnabled(false);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            studentCompletePhone,
                            60,
                            TimeUnit.SECONDS,
                            student_login.this,
                            studentCallback
                    );
                }
            }
        });

        studentCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                student_feedback.setText("Verification Failed");
                student_feedback.setVisibility(View.VISIBLE);
                student_progress.setVisibility(View.INVISIBLE);
                student_loginButton.setEnabled(true);


            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);


                Intent studentOtpIntent = new Intent(student_login.this, verification.class);
                studentOtpIntent.putExtra("AuthCredentials", s);
                startActivity(studentOtpIntent);
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mCurrentUser != null) {
            Intent homeIntent = new Intent(student_login.this, welcome_student.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(homeIntent);
            finish();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(student_login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendUserToHome();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                student_feedback.setVisibility(View.VISIBLE);
                                student_feedback.setText("Invalid OTP");
                            }
                        }
                        student_progress.setVisibility(View.INVISIBLE);
                        student_loginButton.setEnabled(true);
                    }
                });
    }

    public void sendUserToHome(){
        Intent homeIntent = new Intent(student_login.this, welcome_student.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    }
}