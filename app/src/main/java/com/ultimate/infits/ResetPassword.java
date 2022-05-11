package com.ultimate.infits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class ResetPassword extends AppCompatActivity {

    ImageButton b1;
    TextView logtext;
    Button reset;
    TextInputLayout email;
    String sendto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        b1=(ImageButton) findViewById(R.id.imageButton3);
        logtext = (TextView) findViewById(R.id.logtext);
        reset= findViewById(R.id.button3);
        email= findViewById(R.id.textInputLayout8);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassword.super.onBackPressed();
            }
        });

        logtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassword.super.onBackPressed();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendto = email.getEditText().getText().toString();
                Toast.makeText(getApplicationContext(),"Email",Toast.LENGTH_SHORT).show();
             /*   Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
                intent.putExtra(Intent.EXTRA_EMAIL  , sendto);
                intent.putExtra(Intent.EXTRA_SUBJECT, new String[]{"Reset password- Infits"});
                intent.putExtra(Intent.EXTRA_TEXT, new String[]{"Reset the password using this link\n https://www.website.com/forgot-password/?source=SC&country=IN"+});
                try {
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
               }*/

            }
        });
    }
}