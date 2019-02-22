package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    public EditText name;
    public EditText password;
    public Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     public void authenticateLogin(View v){

         name=(EditText)findViewById(R.id.username);
         password=(EditText)findViewById(R.id.password);
         login=(Button)findViewById(R.id.login_button);

         validate(name.getText().toString(), password.getText().toString());
     }
    private void validate(String username, String password){

        if((username.equals("admin"))&&(password.equals("1234"))){
            Intent intent = new Intent(MainActivity.this, NextActivity.class);
            intent.putExtra("username", name.getText().toString());
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "This is not a correct username or password", Toast.LENGTH_LONG).show();
        }

        }

    }
