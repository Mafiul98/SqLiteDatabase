package com.example.sqlitedatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.service.autofill.SaveRequest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    TextView tvdisplay;

    ListView listview;


    DatabaseHelper dbhelper;

    ArrayList<User> datalist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listview=findViewById(R.id.listview);
        dbhelper = new DatabaseHelper(MainActivity2.this);


        Cursor cursor = dbhelper.getAlldata();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String mobile = cursor.getString(2);

            datalist.add(new User(id, name, mobile));


        }


        listview.setAdapter(new myadapter());

    }

    private class myadapter extends BaseAdapter{


        @Override
        public int getCount() {
            return datalist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myview = layoutInflater.inflate(R.layout.item,parent,false);
            TextView tvid = myview.findViewById(R.id.tvid);
            TextView tvname = myview.findViewById(R.id.tvname);
            TextView tvmobile = myview.findViewById(R.id.tvmobile);

            User user = datalist.get(position);

            tvid.setText("Id: " + user.getId());
            tvname.setText("Name: " + user.getName());
            tvmobile.setText("Mobile: " + user.getMobile());


            return myview;
        }
    }

}