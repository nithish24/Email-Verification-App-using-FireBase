package com.example.nithish.loginapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText register_user_name;
    private TextInputEditText register_email;
    private TextInputEditText register_password;
    private TextInputEditText register_con_password;
    private Button register_create;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //initialize variables
        register_user_name = findViewById(R.id.register_user_name);
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_con_password = findViewById(R.id.register_con_password);

        register_create = findViewById(R.id.register_create);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


        register_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = register_user_name.getText().toString();
                String email = register_email.getText().toString();
                String password = register_password.getText().toString();
                String con_password  = register_con_password.getText().toString();
                if(checkpassword(password,con_password)){
                    signinuser(user_name,email,password);
                    Toast.makeText(RegisterActivity.this,"please wait",Toast.LENGTH_SHORT).show();

                }
            }
        });

        }



        private void signinuser(String user_name, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    sendemail();
                    if(!user.isEmailVerified()){
                        Intent intent = new Intent(RegisterActivity.this,StartActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }else{

                    String error = task.getException().toString();
                    Log.d("login",error);
                    Toast.makeText(RegisterActivity.this,error,Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void sendemail() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"check email for verification",Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }


    private boolean checkpassword(String password, String con_password) {
        Log.d("login","in check password");
        if(password.equals(con_password))
            return true;
        else{
            Log.d("login","in toast");
            Toast.makeText(RegisterActivity.this,"password and confirm password has to match",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
