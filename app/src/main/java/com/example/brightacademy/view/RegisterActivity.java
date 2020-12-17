package com.example.brightacademy.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brightacademy.R;
import com.example.brightacademy.dbconnection.DatabaseHandlerUser;
import com.example.brightacademy.model.User;


public class RegisterActivity extends AppCompatActivity {
    EditText fullname,email,password;
    Button register;
    Spinner spinner;
    DatabaseHandlerUser db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db=new DatabaseHandlerUser(this);

        spinner=findViewById(R.id.input_Role);

        fullname=findViewById(R.id.input_FullName);
        email=findViewById(R.id.input_Email);
        password=findViewById(R.id.input_Password);


        register=findViewById(R.id.btnAdd);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fllnm = fullname.getText().toString().trim();
                String emil = email.getText().toString().trim();
                String pswrd = password.getText().toString().trim();

                if(!TextUtils.isEmpty(fllnm) && !TextUtils.isEmpty(emil) && !TextUtils.isEmpty(pswrd)){

                    String roleFromSpinner=spinner.getSelectedItem().toString();
                    db.addUser(new User(fllnm,emil,pswrd,roleFromSpinner));

                    fullname.setText("");
                    email.setText("");
                    password.setText("");
                    spinner.setSelection(0);
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