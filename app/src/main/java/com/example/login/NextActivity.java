package com.example.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NextActivity extends AppCompatActivity {

    private TextView tvLogin;
    private EditText userName, userEmail, userPassword, num;
    private Button register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    String user_email = userEmail.getText().toString().trim();
                    String user_pass = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(NextActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent (NextActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(NextActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NextActivity.this, MainActivity.class));
            }
        });



        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(NextActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
    }


    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.name);
        userEmail = (EditText)findViewById(R.id.email);
        num = (EditText)findViewById(R.id.mob_num);
        userPassword = (EditText)findViewById(R.id.pass);
        register = (Button)findViewById(R.id.sign_up);
        tvLogin = (TextView)findViewById(R.id.textView7);
    }
    private boolean validate(){
        Boolean result = false;

        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();
        String number = num.getText().toString();

        if(name.isEmpty() && password.isEmpty() && email.isEmpty() && number.isEmpty()){

            Toast.makeText(this, "Please provide all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }
}
