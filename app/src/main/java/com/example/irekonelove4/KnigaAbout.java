package com.example.irekonelove4;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.irekonelove4.Models.Kniga;
import com.example.irekonelove4.ui.library.LibraryFragment;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.DTDHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.http.Url;


public class KnigaAbout extends Activity {
    private ImageView avatar;
    private Button[] pages = new Button[6];
    private Button read;
    private Integer last,last_position = 0, kol_str_int;
    private ScrollView main;
    private TextView name, name1, autor, lang, genre, tag,lang_orig,kol_str,who,kol_checks, text, annotation;
    private String img, fulltext;
    private URL url;
    private Layout layout;
    private Bundle bundle;


    private CharSequence mText;
    private int mCurrentIndex = 0;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kniga_about);

        Intent intent = getIntent();
        bundle = intent.getExtras();


        main = findViewById(R.id.main_scroll);
        name1 = findViewById(R.id.name1);
        autor = findViewById(R.id.autor);
        genre = findViewById(R.id.genre);
        tag = findViewById(R.id.tag);
        lang = findViewById(R.id.lang);
        lang_orig = findViewById(R.id.lang1);
        kol_str = findViewById(R.id.kol_str);
        who = findViewById(R.id.who);
        annotation = findViewById(R.id.text_annotation);
        kol_checks = findViewById(R.id.how_many);
        text = findViewById(R.id.text_for_reading);
        read = findViewById(R.id.read_online);
        if(bundle!=null) {
            img = bundle.getString("img");

            name1.setText(bundle.getString("name"));
            autor.setText(bundle.getString("autor"));
            genre.setText(bundle.getString("genre"));
            tag.setText(bundle.getString("tag"));
            lang.setText(bundle.getString("lang"));
            lang_orig.setText(bundle.getString("lang1"));
            who.setText(bundle.getString("who"));
            kol_checks.setText(bundle.getString("how_many"));
            annotation.setText(bundle.getString("annotation"));
            String urll = bundle.getString("url");
            try {
                url = new URL(urll);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


        }
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KnigaAbout.this, Next_Page2.class);
                intent.putExtra("page", String.valueOf(1));
                intent.putExtra("last", last);
                intent.putExtra("text", bundle.getString("text"));
                startActivityForResult(intent, 0);
                overridePendingTransition(R.anim.right_anim, R.anim.alpha_to_zero);

            }
        });
        /*findViewById(R.id.btn_back).setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex > 0) ? mCurrentIndex - 1 : 0;
            update();
        });
        findViewById(R.id.btn_forward).setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex < mPagination.size() - 1) ? mCurrentIndex + 1 : mPagination.size() - 1;
            update();
        });*/


       /* last = Integer.parseInt(kol_str.getText().toString());
        if(last>=6) {
            pages = new Button[6];
            pages[0] = findViewById(R.id.btn1);
            pages[1] = findViewById(R.id.btn2);
            pages[2] = findViewById(R.id.btn3);
            pages[3] = findViewById(R.id.btn4);
            pages[4] = findViewById(R.id.btn5);
            pages[4].setText(String.valueOf(last));
            pages[5] = findViewById(R.id.btn6);
        }
        if(last == 5) {
            pages = new Button[6];
            pages[0] = findViewById(R.id.btn1);
            pages[0].setText("1");
            pages[1] = findViewById(R.id.btn2);
            pages[1].setText("2");
            pages[2] = findViewById(R.id.btn3);
            pages[2].setText("3");
            pages[3] = findViewById(R.id.btn4);
            pages[3].setText("4");
            pages[4] = findViewById(R.id.btn5);
            pages[5] = findViewById(R.id.btn6);
            pages[5].setVisibility(View.GONE);
            pages[4].setText(String.valueOf(last));
        }
        if(last == 4) {
            pages = new Button[6];
            pages[0] = findViewById(R.id.btn1);
            pages[0].setText("1");
            pages[1] = findViewById(R.id.btn2);
            pages[1].setText("2");
            pages[2] = findViewById(R.id.btn3);
            pages[2].setText("3");
            pages[3] = findViewById(R.id.btn4);
            pages[4] = findViewById(R.id.btn5);
            pages[5] = findViewById(R.id.btn6);
            pages[3].setText(String.valueOf(last));
            pages[4].setVisibility(View.GONE);
            pages[5].setVisibility(View.GONE);
        }
        if(last == 3) {
            pages = new Button[6];
            pages[0] = findViewById(R.id.btn1);
            pages[0].setText("1");
            pages[1] = findViewById(R.id.btn2);
            pages[1].setText("2");
            pages[2] = findViewById(R.id.btn3);
            pages[2].setText("3");
            pages[3] = findViewById(R.id.btn4);
            pages[4] = findViewById(R.id.btn5);
            pages[5] = findViewById(R.id.btn6);
            pages[3].setVisibility(View.GONE);
            pages[4].setVisibility(View.GONE);
            pages[5].setVisibility(View.GONE);
        }
        if(last == 2) {
            pages = new Button[6];
            pages[0] = findViewById(R.id.btn1);
            pages[0].setText("1");
            pages[1] = findViewById(R.id.btn2);
            pages[1].setText("2");
            pages[2] = findViewById(R.id.btn3);
            pages[3] = findViewById(R.id.btn4);
            pages[4] = findViewById(R.id.btn5);
            pages[5] = findViewById(R.id.btn6);
            pages[2].setVisibility(View.GONE);
            pages[3].setVisibility(View.GONE);
            pages[4].setVisibility(View.GONE);
            pages[5].setVisibility(View.GONE);
        }
        if(last == 1) {
            pages = new Button[6];
            pages[0] = findViewById(R.id.btn1);
            pages[0].setText("1");
            pages[1] = findViewById(R.id.btn2);
            pages[2] = findViewById(R.id.btn3);
            pages[3] = findViewById(R.id.btn4);
            pages[4] = findViewById(R.id.btn5);
            pages[5] = findViewById(R.id.btn6);
            pages[1].setVisibility(View.GONE);
            pages[2].setVisibility(View.GONE);
            pages[3].setVisibility(View.GONE);
            pages[4].setVisibility(View.GONE);
            pages[5].setVisibility(View.GONE);
        }

        for(int i = 0 ; i < 6; i ++){
            final int finalI = i;

            if(i == 1|| i == 2) {
                pages[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(KnigaAbout.this, Next_Page.class);
                        intent.putExtra("page", String.valueOf(finalI + 1));
                        intent.putExtra("last", last);
                        intent.putExtra("text", bundle.getString("text"));
                        startActivityForResult(intent, 0);
                        overridePendingTransition(R.anim.right_anim, R.anim.alpha_to_zero);
                    }
                });
            }
            if(i == 4) {
                pages[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(KnigaAbout.this, Next_Page.class);
                        intent.putExtra("page", String.valueOf(pages[finalI].getText().toString()));
                        intent.putExtra("last", last);
                        intent.putExtra("text", bundle.getString("text"));
                        startActivityForResult(intent, 0);
                        overridePendingTransition(R.anim.right_anim, R.anim.alpha_to_zero);
                    }
                });
            }
            if(i == 5){
                pages[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(KnigaAbout.this, Next_Page.class);
                        intent.putExtra("page", String.valueOf(Integer.parseInt(pages[0].getText().toString())+1));
                        intent.putExtra("text", bundle.getString("text"));
                        startActivityForResult(intent, 0);
                        overridePendingTransition(R.anim.right_anim, R.anim.alpha_to_zero);
                    }
                });
            }
        }


        new AsyncRequest().execute("123", "/ajax", "foo=bar");



                read = findViewById(R.id.read_online);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[][] loc = new int[1][2];
                name.getLocationOnScreen(loc[0]);
                main.smoothScrollTo(0, last_position +loc[0][1]-175);

                Log.d("1", String.valueOf(loc[0][1]));
            }
        });
        main.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                last_position = scrollY;
            }
        });




        /*for(int i = 0; i < 6; i ++){
            String str = "btn" + String.valueOf(i);
            pages[i] = findViewById(Integer.parseInt(str));
            pages[i].setText(String.valueOf(i));
        }*/


        avatar = findViewById(R.id.avatar);
        Glide.with(this). load ("http://irek.studio/books/books_img/"+img). into (avatar);


        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //avatar.setVisibility(View.VISIBLE);
               //avatar.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom));
                Bundle bundle = null;

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    View view1 = findViewById(R.id.avatar);
                    if (v != null) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(KnigaAbout.this, view1, getApplication().getString(R.string.trans));
                        bundle = options.toBundle();
                    }
                }

                Intent intent = new Intent(KnigaAbout.this, PhotoKitap.class);
                if (bundle == null) {
                    startActivity(intent);
                } else {
                    startActivity(intent, bundle);
                }
            }
        });
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |                                   //скрываем нижнюю панель с кнопками
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |                    //скрываем нижнюю панель с кнопками
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |                         //скрываем нижнюю панель с кнопками
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |                           //скрываем нижнюю панель с кнопками
                        View.SYSTEM_UI_FLAG_FULLSCREEN |                                //скрываем нижнюю панель с кнопками
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        //скрываем нижнюю панель с кнопками
    }

}
