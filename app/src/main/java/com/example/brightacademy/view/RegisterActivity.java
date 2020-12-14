package com.example.brightacademy.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brightacademy.R;
import com.example.brightacademy.dbconnection.DatabaseHandler;
import com.example.brightacademy.model.User;
import com.example.brightacademy.model.UserRole;


public class RegisterActivity extends AppCompatActivity {
    EditText username,fullname,email,password;
    Button register;

    DatabaseHandler db;
//    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db=new DatabaseHandler(this);


        username=findViewById(R.id.input_username);
        fullname=findViewById(R.id.input_Fullname);
        email=findViewById(R.id.input_Email);
        password=findViewById(R.id.input_Password);



        register=findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usrnm = username.getText().toString().trim();
                String fllnm = fullname.getText().toString().trim();
                String emil = email.getText().toString().trim();
                String pswrd = password.getText().toString().trim();



                if(!TextUtils.isEmpty(usrnm) && !TextUtils.isEmpty(fllnm) && !TextUtils.isEmpty(emil) && !TextUtils.isEmpty(pswrd)){

                    db.addUser(new User(usrnm,fllnm,emil,pswrd, UserRole.STUDENT));

                    username.setText("");
                    fullname.setText("");
                    email.setText("");
                    password.setText("");
                    Toast.makeText(RegisterActivity.this, "You have successfully registered",Toast.LENGTH_SHORT ).show();

                }else {

                   Toast.makeText(RegisterActivity.this, "Enter valid input",Toast.LENGTH_SHORT ).show();
                }


            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            //Back button
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}