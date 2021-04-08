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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class teacher_login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser1;

    private EditText teacher_countryCode;
    private EditText teacher_mob;
    private EditText teacher_name;
    private EditText teacher_email;

    private Button teacherLoginButton;
    private ProgressBar teacher_progress;

    private TextView teacher_feedback;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks teacherCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        setTitle("Teacher Login");

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser1 = mAuth.getCurrentUser();

        teacher_countryCode = findViewById(R.id.countrycode1);
        teacher_mob = findViewById(R.id.teacher_mob);
        teacher_name = findViewById(R.id.teacher_name);
        teacher_email = findViewById(R.id.teachers_email);
        teacherLoginButton = findViewById(R.id.teacher_login_button);
        teacher_progress = findViewById(R.id.progressBar2);
        teacher_feedback = findViewById(R.id.feedback2);

        teacherLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teacherCountryCode = teacher_countryCode.getText().toString();
                String teacherPhone = teacher_mob.getText().toString();
                String teacherName = teacher_name.getText().toString();
                String teacherEmail = teacher_email.getText().toString();
                String teacherCompletePhone = teacherCountryCode + teacherPhone;

                if (teacherCountryCode.isEmpty() || teacherPhone.isEmpty() || teacherName.isEmpty() || teacherEmail.isEmpty()) {
                    teacher_feedback.setText("Please fill in the complete form");
                    teacher_feedback.setVisibility(View.VISIBLE);
                } else {
                    teacher_progress.setVisibility(View.VISIBLE);
                    teacherLoginButton.setEnabled(false);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            teacherCompletePhone,
                            60,
                            TimeUnit.SECONDS,
                            teacher_login.this,
                            teacherCallback
                    );

                }
            }
        });

        teacherCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                teacher_feedback.setText("Verification Failed");
                teacher_feedback.setVisibility(View.VISIBLE);
                teacher_progress.setVisibility(View.INVISIBLE);
                teacherLoginButton.setEnabled(true);

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                Intent teacherOtpIntent = new Intent(teacher_login.this, verification_teacher.class);
                teacherOtpIntent.putExtra("AuthCredentials", s);
                startActivity(teacherOtpIntent);
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mCurrentUser1 != null) {
            Intent homeIntent = new Intent(teacher_login.this, welcome_teacher.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(homeIntent);
            finish();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(teacher_login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendUserToHome1();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                teacher_feedback.setVisibility(View.VISIBLE);
                                teacher_feedback.setText("Invalid OTP");
                            }
                        }
                        teacher_progress.setVisibility(View.INVISIBLE);
                        teacherLoginButton.setEnabled(true);
                    }
                });
    }

    public void sendUserToHome1(){
        Intent homeIntent = new Intent(teacher_login.this, welcome_teacher.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    }
}