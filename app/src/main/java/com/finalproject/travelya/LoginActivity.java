package com.finalproject.travelya;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private EditText loginEmail, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_pw);

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                retrieveUserData(user.getUid());
                            }
                        } else {
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                            Toast.makeText(LoginActivity.this, "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void retrieveUserData(String uid) {
        db.collection("users").document(uid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String username = document.getString("username");
                                String email = document.getString("email");
                                String mobile = document.getString("mobile");

                                // Navigate to HomeActivity
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("username", username);
                                intent.putExtra("email", email);
                                intent.putExtra("mobile", mobile);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                            if (errorMessage.contains("Permission denied")) {
                                errorMessage = "You do not have permission to access this data.";
                            }
                            Toast.makeText(LoginActivity.this, "Failed to retrieve user data: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void skipLog(View view) {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    public void logRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}
