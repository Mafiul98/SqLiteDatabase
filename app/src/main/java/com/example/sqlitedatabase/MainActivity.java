package com.example.sqlitedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edname1,edname2;
    Button button1,button2;

    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edname1=findViewById(R.id.edname1);
        edname2=findViewById(R.id.edname2);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        dbhelper = new DatabaseHelper(MainActivity.this);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edname1.length()>0 & edname2.length()>0){
                    dbhelper.insertdata(edname1.getText().toString(),edname2.getText().toString());
                    Toast.makeText(MainActivity.this,"Data has been insrted",Toast.LENGTH_LONG).show();

                }else Toast.makeText(MainActivity.this,"Please enter all the fields",Toast.LENGTH_LONG).show();
                edname1.setText("");
                edname2.setText("");


            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));


            }
        });


    }
}