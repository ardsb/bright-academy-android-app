package com.example.brightacademy.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.brightacademy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView input, input2,register;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        register= findViewById(R.id.btnRgster);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(MainActivity.this,  RegisterActivity.class);
                startActivity(mainActivityIntent);


            }

        });
//        input=findViewById(R.id.input_email);
//        input2=findViewById(R.id.input_password);
//        register= findViewById(R.id.btnGuest);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent mainActivityIntent = new Intent(MainActivity.this, HomepageActivity.class);
////                startActivity(mainActivityIntent);
//
//
//            }
//
//        });
//        login=findViewById(R.id.btnLogin);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String li = input.getText().toString();
//
//                if (!TextUtils.isEmpty(li)){
//                    Toast.makeText(getApplicationContext(), "Email address is " + li,Toast.LENGTH_SHORT).show();
//
//
//
//                }else {
//                    //Toast.makeText(getApplicationContext(), "text cant be empty",Toast.LENGTH_SHORT).show();
//                    input.setError("Email field should be valid");
//
//                }
//
//                String lu = input2.getText().toString();
//
//                if (!TextUtils.isEmpty(li)){
//                    Toast.makeText(getApplicationContext(), "Email address is " + lu,Toast.LENGTH_SHORT).show();
//
//
//
//                }else {
//                    //Toast.makeText(getApplicationContext(), "text cant be empty",Toast.LENGTH_SHORT).show();
//                    input2.setError("Password field should be valid");
//
//                }
//            }
//        });


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
}
