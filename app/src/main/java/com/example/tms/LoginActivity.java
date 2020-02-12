package com.example.tms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
TextView signUp_txt;
Button login_btn;
EditText email,pass;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getActionBar().setTitle("Log In");
        signUp_txt=(TextView)findViewById(R.id.signup_text);
        login_btn=(Button)findViewById(R.id.login_btn);
        email=(EditText)findViewById(R.id.email_login);
        pass=(EditText)findViewById(R.id.pass_login);
        sharedPreferences = getSharedPreferences("Myprf", MODE_PRIVATE);
        final String required_email = sharedPreferences.getString(Constants.USEREMAIL, "Default_email");
        final String required_password = sharedPreferences.getString(Constants.USERPASSWORD, "Default_pass");
        final String loginstatus = sharedPreferences.getString(Constants.LOGINSTATUS, "");

        if (sharedPreferences.getString(Constants.LOGINSTATUS, "").equals(Constants.LOGIN)) {
            Intent main = new Intent(LoginActivity.this, MainActivity.class);

            startActivity(main);
            finish();


        }
        signUp_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                finish();
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"please enter email",Toast.LENGTH_SHORT);
                }
                if(pass.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"please enter password",Toast.LENGTH_SHORT);

                }

                String nemail = email.getText().toString();
                String npass = pass.getText().toString();
                if (nemail.equals(required_email) && npass.equals(required_password)) {
                    Intent main = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(main);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(Constants.LOGINSTATUS, Constants.LOGIN);
                    editor.commit();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
