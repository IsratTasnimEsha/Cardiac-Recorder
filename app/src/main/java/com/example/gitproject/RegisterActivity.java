package com.example.gitproject;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText phone;
    private EditText email;
    private EditText password;
    private Button register;
    private TextView loginUser;


    private ActivityResultLauncher<String> mGetContent;


    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        loginUser = findViewById(R.id.login_user);


        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);

        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUsername = username.getText().toString();
                String txtName = phone.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtName)
                        || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)) {
                    Toast.makeText(RegisterActivity.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtUsername, txtName, txtEmail, txtPassword);
                }
            }
        });
    }

    private void registerUser(final String username, final String name, final String email, String password) {
        pd.setMessage("Please Wait!");
        pd.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("phone", name);
                                map.put("email", email);
                                map.put("username", username);
                                map.put("id", mAuth.getCurrentUser().getUid());

                                mRootRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(map)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    pd.dismiss();
                                                    Toast.makeText(RegisterActivity.this, "Registration successful. Verification email sent.", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    pd.dismiss();
                                                    Toast.makeText(RegisterActivity.this, "Failed to register user.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                pd.dismiss();
                                Toast.makeText(RegisterActivity.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back(View view) {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }

    }




















//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.ContextCompat;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class RegisterActivity extends AppCompatActivity {
//
//    private  EditText username, email, phone, password;
//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        username = findViewById(R.id.username);
//        email = findViewById(R.id.email);
//        phone = findViewById(R.id.phone);
//        password = findViewById(R.id.password);
//    }
//
//    public void registerPage(View view) {
//        String st_username = username.getText().toString();
//        String st_email = email.getText().toString();
//        String st_password = password.getText().toString();
//        String st_phone = phone.getText().toString();
//
//        if (st_email.isEmpty()) {
//            email.setError("Email Address Is Required.");
//            Toast.makeText(this, "Email Address Is Required.", Toast.LENGTH_SHORT).show();
//        } else if (st_phone.isEmpty()) {
//            phone.setError("Phone Number Is Required.");
//            Toast.makeText(this, "Phone Number Is Required.", Toast.LENGTH_SHORT).show();
//        } else if (st_username.isEmpty()) {
//            username.setError("Username Is Required.");
//            Toast.makeText(this, "Username Is Required.", Toast.LENGTH_SHORT).show();
//        } else if (st_password.length() < 6) {
//            password.setError("Password Must Be At Least 6 Characters.");
//            Toast.makeText(this, "Password Must Be At Least 6 Characters.", Toast.LENGTH_SHORT).show();
//        }
////        else {
////            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
////                @Override
////                public void onDataChange(@NonNull DataSnapshot snapshot) {
////                    if (snapshot.hasChild(st_phone)) {
////                        Toast.makeText(RegisterActivity.this,
////                                "Phone Number Is Already Registered.", Toast.LENGTH_SHORT).show();
////                    }
////                    else {
////                        databaseReference.child("users").child(st_phone).child("User_Name").setValue(st_username);
////                        databaseReference.child("users").child(st_phone).child("Phone").setValue(st_phone);
////                        databaseReference.child("users").child(st_phone).child("EMail").setValue(st_email);
////                        databaseReference.child("users").child(st_phone).child("Password").setValue(st_password);
////
////                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
////                    }
////                }
//
////                @Override
////                public void onCancelled(@NonNull DatabaseError error) {
////
////                }
////            });
//
//        FirebaseAuth mAuth;
//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//        @Override
//        public void onComplete(@NonNull Task<AuthResult> task) {
//            if (task.isSuccessful()) {
//                UserDataType userObj = new UserDataType(username, email, phone, password);
//                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userObj).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            mAuth.getCurrentUser().sendEmailVerification();
//                            errorMassageText.setText("Verification link has sent to your email.");
//                            errorMassageText.setTextColor(ContextCompat.getColor(RegisterActivity.this, R.color.success));
//                            errorMassageText.setVisibility(View.VISIBLE);
//                            progressBar.setVisibility(View.GONE);
//                               /* startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
//                                finish();*/
//                        } else {
//                            progressBar.setVisibility(View.GONE);
//                            errorMassageText.setText("Registration Failed!");
//                            errorMassageText.setTextColor(ContextCompat.getColor(RegisterActivity.this, R.color.error));
//                            progressBar.setVisibility(View.GONE);
//                            errorMassageText.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });
//
//            } else {
//                progressBar.setVisibility(View.GONE);
//                errorMassageText.setText("You are already registered!");
//                errorMassageText.setTextColor(ContextCompat.getColor(RegisterActivity.this, R.color.error));
//                errorMassageText.setVisibility(View.VISIBLE);
//            }
//        }
//    });
//
//
//    public void loginPage(View view) {
//        startActivity(new Intent(this, LoginActivity.class));
//    }
//}