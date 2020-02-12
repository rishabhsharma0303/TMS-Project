package com.example.tms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
TextView login_txt;
EditText name,email,password,confirm_password;
Button singUp_btn;
  //  SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("SignUp Form");
        name= (EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        confirm_password=(EditText) findViewById(R.id.confirm_password);
        singUp_btn=(Button)findViewById(R.id.signUp_btn);
      //  pref = getSharedPreferences("Registration", 0);
        // get editor to edit in file
/*       // editor = pref.edit();
login_txt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        finish();
    }
});*/

        singUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().length()<=0){
                    Toast.makeText(SignUpActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                }
                else if( email.getText().length()<=0){
                    Toast.makeText(SignUpActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                }
                else if( password.getText().length()<=0){
                    Toast.makeText(SignUpActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                } else if(confirm_password.getText().length()<=0){
                    Toast.makeText(SignUpActivity.this, "Enter confirm password", Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences sharedPreferences=getSharedPreferences("Myprf",MODE_PRIVATE);

                    // as now we have information in string. Lets stored them with the help of editor
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    String newname=name.getText().toString();
                    String newemail=email.getText().toString();
                    String newpass=password.getText().toString();
                    /*editor.putString("Name", Constants.USERNAME);
                    editor.putString("Email", Constants.USEREMAIL);
                    editor.putString("Password", Constants.USERPASSWORD);*/
                    editor.putString(Constants.USERNAME,newname);
                    editor.putString(Constants.USEREMAIL,newemail);
                    editor.putString(Constants.USERPASSWORD,newpass);
                    editor.commit();   // commit the values

                    // after saving the value open next activity
                    Intent ob = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(ob);

                }
            }
        });

    }
}
