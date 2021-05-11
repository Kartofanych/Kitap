package com.example.irekonelove4;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.irekonelove4.DataRequest.BookForJson;
import com.example.irekonelove4.DataRequest.BookNotForJson;
import com.example.irekonelove4.Models.Kniga;

import java.util.ArrayList;
import java.util.List;

public class Adapter_for_Home extends PagerAdapter {

    private List<BookNotForJson> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter_for_Home(List<BookNotForJson> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        super.finishUpdate(container);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d("hi", "hi");
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_kitap_in_home, container, false);
        Log.d("fuck", models.get(position).getName() +" "+ String.valueOf(position));
        ImageView imageView;
        TextView name, autor;

        imageView = view.findViewById(R.id.KitapImage);
        name = view.findViewById(R.id.name);
        autor = view.findViewById(R.id.autor);
        try {
            Glide.with(context).load("http://irek.studio/books/books_img/" + models.get(position).getImg()).into(imageView);
        }catch (Exception e){

        }
        name.setText(models.get(position).getName());
        autor.setText(models.get(position).getAuthor());

       /* view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KnigaAbout.class);
                //intent.putExtra("param", models.get(position).getName());
                context.startActivity(intent);
                finish();
            }
        });*/

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
