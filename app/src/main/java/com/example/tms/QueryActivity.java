package com.example.tms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

public class QueryActivity extends AppCompatActivity {
Button submit;
TextView txt_query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        submit=(Button)findViewById(R.id.btn_submit);
        txt_query=(TextView)findViewById(R.id.text_query);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sendMail();
            }

            private void sendMail() {
             //   String receipientList="rishabh.sharma.0303@gmail.com";
                String getText=txt_query.getText().toString();
                Intent intent=new Intent(Intent.ACTION_SEND);
           //     intent.putExtra(Intent.EXTRA_EMAIL,receipientList);
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"rishabh.sharma.0303@gmail.com"});
                intent.putExtra(Intent.EXTRA_TEXT,getText);
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent,"Choose an email client"));
                finish();
            }
        });
    }
}
