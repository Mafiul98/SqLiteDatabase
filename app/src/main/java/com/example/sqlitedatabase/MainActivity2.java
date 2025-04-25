package com.example.sqlitedatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.service.autofill.SaveRequest;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    TextView tvdisplay;

    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvdisplay=findViewById(R.id.tvdisplay);
        dbhelper = new DatabaseHelper(MainActivity2.this);


        Cursor cursor = dbhelper.getAlldata();
        tvdisplay.setText("total data: "+cursor.getCount());

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String mobile = cursor.getString(2);

            tvdisplay.append("\nid: "+id+" name: "+name+"\n"+" mobile: "+mobile);
        }


    }
}