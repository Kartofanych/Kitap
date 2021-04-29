package com.example.irekonelove4.ui.addition;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.irekonelove4.R;
import com.example.irekonelove4.AddingBook;

public class AdditionFragment extends Fragment {

    private AdditionViewModel additionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_addition, container, false);
        TextView tools_name = (TextView) view.findViewById(R.id.fragment_name);

        tools_name.setText("Добавление");

        Button dalee = view.findViewById(R.id.dalee);
        dalee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddingBook.class);
                startActivity(intent);
            }
        });
        return view;
    }
}