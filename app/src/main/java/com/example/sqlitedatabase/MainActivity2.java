package com.example.sqlitedatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.service.autofill.SaveRequest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    SearchView searchview;

    ListView listview;
    DatabaseHelper dbhelper;
    ArrayList<User> datalist = new ArrayList<>();
    UserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listview=findViewById(R.id.listview);
        searchview=findViewById(R.id.searchview);
        dbhelper = new DatabaseHelper(MainActivity2.this);


        Cursor cursor = dbhelper.getAlldata();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String mobile = cursor.getString(2);
            datalist.add(new User(id, name, mobile));

        }

        adapter = new UserAdapter(this,datalist,dbhelper);
        listview.setAdapter(adapter);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }
}