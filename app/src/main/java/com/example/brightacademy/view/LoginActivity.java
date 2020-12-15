package com.example.brightacademy.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.brightacademy.R;
import com.example.brightacademy.dbconnection.DatabaseHandler;
import com.example.brightacademy.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class
LoginActivity extends AppCompatActivity {
    TextView email, password,register;
    DatabaseHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db=new DatabaseHandler(this);

        email=findViewById(R.id.inputEmail);
        password=findViewById(R.id.inputPassword);

        register= findViewById(R.id.btnRgster);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(LoginActivity.this,  RegisterActivity.class);
                startActivity(mainActivityIntent);


            }

        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLoginClick(View view) {

        String emailEntererd = this.email.getText().toString();
        String passwordEntered = this.password.getText().toString();
        User validateUser = db.validateUser(emailEntererd, passwordEntered);
        if (validateUser != null) {
            finish();
            switch (validateUser.getUserRole()) {
                case STUDENT:
                    startActivity(new Intent(this, StudentCourseActivity.class));
                    break;
                case ADMIN:
                    startActivity(new Intent(this, AdminCourseActivity.class));
                    break;
            }
        }else{
            Toast.makeText(this,"Your login credentials are invalid",Toast.LENGTH_SHORT).show();
        }

    }
}
