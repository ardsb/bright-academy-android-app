package com.example.brightacademy.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.brightacademy.R;
import com.example.brightacademy.dbconnection.DatabaseHandlerUser;
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
    DatabaseHandlerUser db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db=new DatabaseHandlerUser(this);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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

            switch (validateUser.getUserRole()) {
                case STUDENT:
                    Intent intent = new Intent(this, StudentDashboardActivity.class);
                    intent.putExtra("userId",validateUser.getId());
                    startActivity(intent);

                    break;
                case ADMIN:
                    startActivity(new Intent(this, AdminCourseListActivity.class));
                    break;
            }
        }else{
            Toast.makeText(this,"Your password or email is incorrect",Toast.LENGTH_SHORT).show();
        }



    }
}
