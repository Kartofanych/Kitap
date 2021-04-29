package com.example.irekonelove4.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.irekonelove4.R;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.new_listitem, parent,false);
        }
        TextView name = (TextView)convertView.findViewById(R.id.name);
        TextView about =(TextView) convertView.findViewById(R.id.about);
        name.setText(user.name);
        about.setText(user.about);

        return convertView;
    }
}