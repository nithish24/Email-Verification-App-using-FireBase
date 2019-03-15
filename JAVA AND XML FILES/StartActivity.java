package com.example.nithish.loginapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {


    private TextInputEditText start_user_name;
    private TextInputEditText start_password;

    private Button start_register_btn;
    private Button start_login_btn;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start_login_btn = findViewById(R.id.start_login_btn);
        start_user_name = findViewById(R.id.start_user_name);
        start_password = findViewById(R.id.start_password);
        start_register_btn = (Button)findViewById(R.id.start_register_btn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();





        start_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = start_user_name.getText().toString();
                String password = start_password.getText().toString();
                if(username.isEmpty() && password.isEmpty()){
                    Toast.makeText(StartActivity.this,"fill in the empty field",Toast.LENGTH_SHORT).show();
                }else{
                    usersignin(username,password);
                    progressDialog.setTitle("User Log-in");
                    progressDialog.setMessage("checking database...please wait");
                    progressDialog.show();
                }

            }


        });

        start_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });



    }

    private void usersignin(String username, String password) {
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    progressDialog.dismiss();
                    Intent tomainintent = new Intent(StartActivity.this,MainActivity.class);
                    startActivity(tomainintent);
                    finish();
                }else{
                    progressDialog.dismiss();
                    String error = task.getException().toString();
                    Toast.makeText(StartActivity.this,error,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
