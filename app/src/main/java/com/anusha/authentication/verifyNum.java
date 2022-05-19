package com.anusha.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class verifyNum extends AppCompatActivity {
    TextView phoneNum;
    PinView pinView;
    Button btn;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifynum);
        phoneNum = findViewById(R.id.phoneNumber);
        pinView = findViewById(R.id.pin_view);
        btn = findViewById(R.id.verify);
        mAuth = FirebaseAuth.getInstance();
        String verificationID = getIntent().getStringExtra("verificationID");
        String number = getIntent().getStringExtra("number");

        phoneNum.setVisibility(View.GONE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, pinView.getText().toString());
                signInWithPhoneAuthCredential(credential);

            }
        });


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            Toast.makeText(verifyNum.this, "successfully verified", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            sendToProfileSelection();
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void sendToProfileSelection() {
        Intent intent = new Intent(verifyNum.this, profileSelection.class);
        startActivity(intent);
        finish();
    }
}