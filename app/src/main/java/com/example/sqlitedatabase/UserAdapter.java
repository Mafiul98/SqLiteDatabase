package com.example.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class UserAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<User> orginalList;
    ArrayList<User> filteredList;
    DatabaseHelper dbhelper;

    public UserAdapter(Context context,ArrayList<User> datalist,DatabaseHelper dbhelper){
        this.context = context;
        this.orginalList = datalist;
        this.filteredList = new ArrayList<>(datalist);
        this.dbhelper = dbhelper;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View myview  = layoutInflater.inflate(R.layout.item,parent,false);

        TextView tvid = myview.findViewById(R.id.tvid);
        TextView tvname = myview.findViewById(R.id.tvname);
        TextView tvmobile = myview.findViewById(R.id.tvmobile);
        ImageView imageview = myview.findViewById(R.id.imageview);

        User user = filteredList.get(position);
        tvid.setText("Id: "+user.getId());
        tvname.setText("Name: "+user.getName());
        tvmobile.setText("Mobile: "+user.getMobile());

        imageview.setOnClickListener(v -> {

            dbhelper.deleteone(user.getId());
            orginalList.remove(user);
            filteredList.remove(user);
            notifyDataSetChanged();
            Toast.makeText(context, "Data deleted: " + user.getId(), Toast.LENGTH_SHORT).show();


        });

        return myview;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<User> tempList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    tempList.addAll(orginalList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim();
                    for (User user : orginalList) {
                        if (user.getName().toLowerCase(Locale.ROOT).contains(filterPattern)
                                || user.getMobile().toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            tempList.add(user);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = tempList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList.clear();
                filteredList.addAll((ArrayList<User>) results.values);
                notifyDataSetChanged();

            }
        };
    }
}
