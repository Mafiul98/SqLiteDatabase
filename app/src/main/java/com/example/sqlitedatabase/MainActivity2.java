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
    myadapter adapter;


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

        adapter = new myadapter();
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

    private class myadapter extends BaseAdapter implements Filterable {

        ArrayList<User> filterdlist = new ArrayList<>(datalist);

        @Override
        public int getCount() {
            return filterdlist.size();
        }

        @Override
        public Object getItem(int position) {
            return filterdlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myview  = layoutInflater.inflate(R.layout.item,parent,false);
            TextView tvid = myview.findViewById(R.id.tvid);
            TextView tvname = myview.findViewById(R.id.tvname);
            TextView tvmobile = myview.findViewById(R.id.tvmobile);
            ImageView imageview = myview.findViewById(R.id.imageview);

            User user = filterdlist.get(position);
            tvid.setText("Id: " + user.getId());
            tvname.setText("Name: " + user.getName());
            tvmobile.setText("Mobile: " + user.getMobile());

            imageview.setOnClickListener(v ->{
                dbhelper.deleteone(user.getId());
                datalist.remove(user);
                filterdlist.remove(user);
                notifyDataSetChanged();
                Toast.makeText(MainActivity2.this, "Data deleted: " + user.getId(), Toast.LENGTH_SHORT).show();

            });

            return myview;
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    ArrayList<User> templist = new ArrayList<>();
                    if (constraint == null || constraint.length()==0){
                        templist.addAll(datalist);
                    }else {
                        String filterpattren = constraint.toString().toLowerCase().trim();
                        for (User user : datalist) {
                            if (user.getName().toLowerCase().contains(filterpattren)
                                    || user.getMobile().toLowerCase().contains(filterpattren)) {
                                templist.add(user);
                            }
                        }
                    }
                    FilterResults results = new FilterResults();
                    results.values = templist;

                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filterdlist.clear();
                    filterdlist.addAll((ArrayList<User>) results.values);
                    notifyDataSetChanged();

                }
            };
        }
    }
}