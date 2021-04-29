package com.example.irekonelove4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.example.irekonelove4.ui.search.SearchFragment;

import java.util.ArrayList;

public class Next_Page extends FragmentActivity {



    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    private TextView text;
    private Button[] pages;
    private String firstpage, fulltext;
    private Integer lastpage, where = 1;

    public Drawable no_pages;
    public Drawable yes_pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_page_2);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        no_pages = getResources().getDrawable(R.drawable.designfor_nopages_but);
        yes_pages = getResources().getDrawable(R.drawable.designfor_pages_but);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            firstpage = extras.getString("page");
            lastpage = extras.getInt("last");
            fulltext = extras.getString("text");
            Log.d("1",firstpage);
            // and get whatever type user account id is
        }

        where = Integer.parseInt(firstpage) - 1;
        pager.setAdapter(pagerAdapter);
        text = pager.findViewById(R.id.text);
        text.setText("1");



        pages = new Button[9];

        if(lastpage>=6) {
            pages[0] = findViewById(R.id.btn0);
            pages[1] = findViewById(R.id.btn1);
            pages[1].setText(firstpage);
            pages[2] = findViewById(R.id.btn2);
            pages[3] = findViewById(R.id.btn3);
            pages[4] = findViewById(R.id.btn4);
            pages[5] = findViewById(R.id.btn5);
            pages[6] = findViewById(R.id.btn6);
            pages[7] = findViewById(R.id.btn7);
            pages[8] = findViewById(R.id.btn8);
        }
        if(lastpage == 5) {
            pages[0] = findViewById(R.id.btn0);
            pages[1] = findViewById(R.id.btn1);
            pages[1].setText("1");
            pages[2] = findViewById(R.id.btn2);
            pages[2].setText("2");
            pages[3] = findViewById(R.id.btn3);
            pages[3].setText("3");
            pages[4] = findViewById(R.id.btn4);
            pages[4].setText("4");
            pages[5] = findViewById(R.id.btn5);
            pages[5].setText("5");
            pages[6] = findViewById(R.id.btn6);

            pages[7] = findViewById(R.id.btn7);
            pages[8] = findViewById(R.id.btn8);

            pages[7].setVisibility(View.GONE);
            pages[8].setVisibility(View.GONE);
        }
        if(lastpage == 4) {
            pages[0] = findViewById(R.id.btn0);
            pages[1] = findViewById(R.id.btn1);
            pages[1].setText("1");
            pages[2] = findViewById(R.id.btn2);
            pages[2].setText("2");
            pages[3] = findViewById(R.id.btn3);
            pages[3].setText("3");
            pages[4] = findViewById(R.id.btn4);
            pages[4].setText("4");
            pages[5] = findViewById(R.id.btn5);
            pages[6] = findViewById(R.id.btn6);

            pages[7] = findViewById(R.id.btn7);
            pages[8] = findViewById(R.id.btn8);
            pages[6].setVisibility(View.GONE);
            pages[7].setVisibility(View.GONE);
            pages[5].setVisibility(View.GONE);
        }
        if(lastpage == 3) {
            pages[0] = findViewById(R.id.btn0);
            pages[1] = findViewById(R.id.btn1);
            pages[1].setText("1");
            pages[2] = findViewById(R.id.btn2);
            pages[2].setText("2");
            pages[3] = findViewById(R.id.btn3);
            pages[3].setText("3");
            pages[4] = findViewById(R.id.btn4);
            pages[5] = findViewById(R.id.btn5);
            pages[6] = findViewById(R.id.btn6);
            pages[7] = findViewById(R.id.btn7);
            pages[8] = findViewById(R.id.btn8);

            pages[5].setVisibility(View.GONE);
            pages[6].setVisibility(View.GONE);
            pages[7].setVisibility(View.GONE);
            pages[4].setVisibility(View.GONE);
        }
        if(lastpage == 2) {
            pages[0] = findViewById(R.id.btn0);
            pages[1] = findViewById(R.id.btn1);
            pages[1].setText("1");
            pages[2] = findViewById(R.id.btn2);
            pages[2].setText("2");
            pages[3] = findViewById(R.id.btn3);
            pages[4] = findViewById(R.id.btn4);
            pages[5] = findViewById(R.id.btn5);
            pages[6] = findViewById(R.id.btn6);
            pages[7] = findViewById(R.id.btn7);
            pages[8] = findViewById(R.id.btn8);

            pages[4].setVisibility(View.GONE);
            pages[5].setVisibility(View.GONE);
            pages[6].setVisibility(View.GONE);
            pages[7].setVisibility(View.GONE);
            pages[3].setVisibility(View.GONE);
        }
        if(lastpage == 1) {
            pages[0] = findViewById(R.id.btn0);
            pages[1] = findViewById(R.id.btn1);
            pages[1].setText("1");
            pages[2] = findViewById(R.id.btn2);
            pages[3] = findViewById(R.id.btn3);
            pages[4] = findViewById(R.id.btn4);
            pages[5] = findViewById(R.id.btn5);
            pages[6] = findViewById(R.id.btn6);
            pages[7] = findViewById(R.id.btn7);
            pages[8] = findViewById(R.id.btn8);

            pages[3].setVisibility(View.GONE);
            pages[4].setVisibility(View.GONE);
            pages[5].setVisibility(View.GONE);
            pages[6].setVisibility(View.GONE);
            pages[7].setVisibility(View.GONE);
            pages[2].setVisibility(View.GONE);
        }

        FuckIt(where);
            for (int i = 0; i < 9; i++) {
                final int finalI = i;
                if (i == 0) {
                    pages[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            where--;
                            FuckIt(where);
                        }
                    });
                } else if (i == 2 && pages[i].getText().equals("...")) {
                    pages[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            where = where - 2;
                            FuckIt(where);
                        }
                    });
                } else if (i == 6 && pages[i].getText().equals("...")) {
                    pages[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            where = where + 2;
                            FuckIt(where);
                        }
                    });
                } else if ((i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7) && !(pages[i].getText().equals("..."))) {
                    pages[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            where = Integer.parseInt(pages[finalI].getText().toString());
                            FuckIt(where - 1);
                        }
                    });
                } else if (i == 8) {

                    pages[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            where++;
                            FuckIt(where);

                        }
                    });
                }
            }

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position != where){
                    where = position;
                    FuckIt(where);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void FuckIt(Integer where) {
        Log.d("1", String.valueOf(where));

        pager.setCurrentItem(where);
        //text = pager.findViewById(R.id.text);
//        text.setText(fulltext.substring(1540*(where), 1540*(where+1)));
        if(lastpage>=6) {
            if (where == 0) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.VISIBLE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.GONE);
                pages[2].setVisibility(View.GONE);
                pages[1].setVisibility(View.GONE);
                pages[0].setVisibility(View.GONE);

                pages[4].setText("1");
                pages[5].setText("2");
                pages[6].setText("...");
                pages[7].setText(lastpage.toString());
            } else if (where == 1) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.VISIBLE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.GONE);
                pages[1].setVisibility(View.GONE);
                pages[0].setVisibility(View.VISIBLE);

                pages[3].setText("1");
                pages[4].setText("2");
                pages[5].setText("3");
                pages[6].setText("...");
                pages[7].setText(lastpage.toString());
            } else if (where == 2) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.VISIBLE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.GONE);
                pages[0].setVisibility(View.VISIBLE);

                pages[2].setText("1");
                pages[3].setText("2");
                pages[4].setText("3");
                pages[5].setText("4");
                pages[6].setText("...");
                pages[7].setText(lastpage.toString());
            } else if (where == 3) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.VISIBLE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.VISIBLE);
                pages[0].setVisibility(View.VISIBLE);

                pages[1].setText("1");
                pages[2].setText("2");
                pages[3].setText("3");
                pages[4].setText("4");
                pages[5].setText("5");
                pages[6].setText("...");
                pages[7].setText(lastpage.toString());
            } else if (where == lastpage - 1) {
                pages[8].setVisibility(View.GONE);
                pages[7].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);
                pages[5].setVisibility(View.GONE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.VISIBLE);
                pages[0].setVisibility(View.VISIBLE);
                pages[4].setText(lastpage.toString());
                pages[3].setText(String.valueOf(lastpage - 1));
                pages[2].setText("...");
                pages[1].setText("1");

            } else if (where == lastpage - 2) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.VISIBLE);
                pages[0].setVisibility(View.VISIBLE);

                pages[5].setText(lastpage.toString());
                pages[4].setText(String.valueOf(lastpage - 1));
                pages[3].setText(String.valueOf(lastpage - 2));
                pages[2].setText("...");
                pages[1].setText("1");
            } else if (where == lastpage - 3) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.GONE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.VISIBLE);
                pages[0].setVisibility(View.VISIBLE);

                pages[7].setText(lastpage.toString());
                pages[5].setText(String.valueOf(lastpage - 1));
                pages[4].setText(String.valueOf(lastpage - 2));
                pages[3].setText(String.valueOf(lastpage - 3));
                pages[2].setText("...");
                pages[1].setText("1");
            } else if (where == lastpage - 4) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.VISIBLE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.VISIBLE);
                pages[0].setVisibility(View.VISIBLE);

                pages[7].setText(lastpage.toString());
                pages[6].setText(String.valueOf(lastpage - 1));
                pages[5].setText(String.valueOf(lastpage - 2));
                pages[4].setText(String.valueOf(lastpage - 3));
                pages[3].setText(String.valueOf(lastpage - 4));
                pages[2].setText("...");
                pages[1].setText("1");
            } else if (where < lastpage - 4 || where > 3) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.VISIBLE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.VISIBLE);
                pages[0].setVisibility(View.VISIBLE);

                pages[7].setText(lastpage.toString());
                pages[6].setText("...");
                pages[5].setText(String.valueOf(where + 2));
                pages[4].setText(String.valueOf(where + 1));
                pages[3].setText(String.valueOf(where));
                pages[2].setText("...");
                pages[1].setText("1");
            }
        }

        if(lastpage == 5) {
            if(where == 0) {
                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.GONE);
                pages[1] = findViewById(R.id.btn1);
                pages[1].setText("1");
                pages[1].setTextColor(getResources().getColor(R.color.White));
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[3].setText("3");
                pages[4] = findViewById(R.id.btn4);
                pages[4].setText("4");
                pages[5] = findViewById(R.id.btn5);
                pages[5].setText("5");
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.VISIBLE);
                pages[7] = findViewById(R.id.btn7);
                pages[6] = findViewById(R.id.btn6);

                pages[7].setVisibility(View.GONE);
                pages[8].setVisibility(View.GONE);


                pages[1].setBackground(yes_pages);
                pages[2].setBackground(no_pages);
                pages[3].setBackground(no_pages);
                pages[4].setBackground(no_pages);
                pages[5].setBackground(no_pages);
                pages[4].setTextColor(getResources().getColor(R.color.Black));
                pages[2].setTextColor(getResources().getColor(R.color.Black));
                pages[3].setTextColor(getResources().getColor(R.color.Black));
                pages[5].setTextColor(getResources().getColor(R.color.Black));
            }
            if(where == 1) {

                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.VISIBLE);
                pages[1] = findViewById(R.id.btn1);
                pages[1].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[3].setText("3");
                pages[4] = findViewById(R.id.btn4);
                pages[4].setText("4");
                pages[5] = findViewById(R.id.btn5);
                pages[5].setText("5");
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.VISIBLE);
                pages[7] = findViewById(R.id.btn7);
                pages[6] = findViewById(R.id.btn6);

                pages[7].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);

                pages[2].setBackground(yes_pages);
                pages[1].setBackground(no_pages);
                pages[3].setBackground(no_pages);
                pages[4].setBackground(no_pages);
                pages[5].setBackground(no_pages);
                pages[1].setTextColor(getResources().getColor(R.color.Black));
                pages[4].setTextColor(getResources().getColor(R.color.Black));
                pages[3].setTextColor(getResources().getColor(R.color.Black));
                pages[5].setTextColor(getResources().getColor(R.color.Black));
                pages[2].setTextColor(getResources().getColor(R.color.White));
            }
            if(where == 2) {

                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.VISIBLE);
                pages[1] = findViewById(R.id.btn1);
                pages[1].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[3].setText("3");
                pages[4] = findViewById(R.id.btn4);
                pages[4].setText("4");
                pages[5] = findViewById(R.id.btn5);
                pages[5].setText("5");
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.VISIBLE);
                pages[7] = findViewById(R.id.btn7);
                pages[6] = findViewById(R.id.btn6);

                pages[7].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);

                pages[3].setBackground(yes_pages);
                pages[1].setBackground(no_pages);
                pages[2].setBackground(no_pages);
                pages[4].setBackground(no_pages);
                pages[1].setTextColor(getResources().getColor(R.color.Black));
                pages[2].setTextColor(getResources().getColor(R.color.Black));
                pages[4].setTextColor(getResources().getColor(R.color.Black));
                pages[5].setTextColor(getResources().getColor(R.color.Black));
                pages[3].setTextColor(getResources().getColor(R.color.White));
                pages[5].setBackground(no_pages);
            }
            if(where == 3) {

                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.VISIBLE);
                pages[1] = findViewById(R.id.btn1);
                pages[1].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[3].setText("3");
                pages[4] = findViewById(R.id.btn4);
                pages[4].setText("4");
                pages[5] = findViewById(R.id.btn5);
                pages[5].setText("5");
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.VISIBLE);
                pages[7] = findViewById(R.id.btn7);
                pages[6] = findViewById(R.id.btn6);

                pages[7].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);

                pages[4].setBackground(yes_pages);
                pages[1].setBackground(no_pages);
                pages[3].setBackground(no_pages);
                pages[2].setBackground(no_pages);
                pages[5].setBackground(no_pages);
                pages[1].setTextColor(getResources().getColor(R.color.Black));
                pages[2].setTextColor(getResources().getColor(R.color.Black));
                pages[3].setTextColor(getResources().getColor(R.color.Black));
                pages[5].setTextColor(getResources().getColor(R.color.Black));
                pages[4].setTextColor(getResources().getColor(R.color.White));
            }
            if(where == 4){

                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.VISIBLE);
                pages[1] = findViewById(R.id.btn1);
                pages[1].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[3].setText("3");
                pages[4] = findViewById(R.id.btn4);
                pages[4].setText("4");
                pages[5] = findViewById(R.id.btn5);
                pages[5].setText("5");
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.GONE);
                pages[7] = findViewById(R.id.btn7);
                pages[6] = findViewById(R.id.btn6);

                pages[7].setVisibility(View.GONE);
                pages[8].setVisibility(View.GONE);

                pages[5].setBackground(yes_pages);
                pages[2].setBackground(no_pages);
                pages[3].setBackground(no_pages);
                pages[4].setBackground(no_pages);
                pages[1].setBackground(no_pages);
                pages[5].setTextColor(getResources().getColor(R.color.White));
                pages[1].setTextColor(getResources().getColor(R.color.Black));
                pages[2].setTextColor(getResources().getColor(R.color.Black));
                pages[3].setTextColor(getResources().getColor(R.color.Black));
                pages[4].setTextColor(getResources().getColor(R.color.Black));
            }

        }
        if(lastpage == 4) {
            if(where == 0) {
                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.GONE);
                pages[1] = findViewById(R.id.btn1);
                pages[1].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[3].setText("3");
                pages[4] = findViewById(R.id.btn4);
                pages[4].setText("4");
                pages[5] = findViewById(R.id.btn5);
                pages[6] = findViewById(R.id.btn6);

                pages[7] = findViewById(R.id.btn7);
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.GONE);
                pages[7].setVisibility(View.GONE);
                pages[5].setVisibility(View.GONE);

                pages[1].setBackground(yes_pages);
                pages[2].setBackground(no_pages);
                pages[3].setBackground(no_pages);
                pages[4].setBackground(no_pages);
                pages[1].setTextColor(getResources().getColor(R.color.White));
                pages[2].setTextColor(getResources().getColor(R.color.Black));
                pages[3].setTextColor(getResources().getColor(R.color.Black));
                pages[4].setTextColor(getResources().getColor(R.color.Black));
            }
            if(where == 1){
                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.VISIBLE);
                pages[1] = findViewById(R.id.btn1);
                pages[1].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[3].setText("3");
                pages[4] = findViewById(R.id.btn4);
                pages[4].setText("4");
                pages[5] = findViewById(R.id.btn5);
                pages[6] = findViewById(R.id.btn6);

                pages[7] = findViewById(R.id.btn7);
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.GONE);
                pages[7].setVisibility(View.GONE);
                pages[5].setVisibility(View.GONE);

                pages[2].setBackground(yes_pages);
                pages[1].setBackground(no_pages);
                pages[3].setBackground(no_pages);
                pages[4].setBackground(no_pages);
                pages[2].setTextColor(getResources().getColor(R.color.White));
                pages[1].setTextColor(getResources().getColor(R.color.Black));
                pages[3].setTextColor(getResources().getColor(R.color.Black));
                pages[4].setTextColor(getResources().getColor(R.color.Black));
            }
            if(where == 2){
                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.VISIBLE);
                pages[1] = findViewById(R.id.btn1);
                pages[1].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[3].setText("3");
                pages[4] = findViewById(R.id.btn4);
                pages[4].setText("4");
                pages[5] = findViewById(R.id.btn5);
                pages[6] = findViewById(R.id.btn6);

                pages[7] = findViewById(R.id.btn7);
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.GONE);
                pages[7].setVisibility(View.GONE);
                pages[5].setVisibility(View.GONE);

                pages[3].setBackground(yes_pages);
                pages[1].setBackground(no_pages);
                pages[2].setBackground(no_pages);
                pages[4].setBackground(no_pages);
                pages[2].setTextColor(getResources().getColor(R.color.Black));
                pages[1].setTextColor(getResources().getColor(R.color.Black));
                pages[4].setTextColor(getResources().getColor(R.color.Black));
                pages[3].setTextColor(getResources().getColor(R.color.White));
            }
            if(where == 3){
                    pages[0] = findViewById(R.id.btn0);
                    pages[0].setVisibility(View.VISIBLE);
                    pages[1] = findViewById(R.id.btn1);
                    pages[1].setText("1");
                    pages[2] = findViewById(R.id.btn2);
                    pages[2].setText("2");
                    pages[3] = findViewById(R.id.btn3);
                    pages[3].setText("3");
                    pages[4] = findViewById(R.id.btn4);
                    pages[4].setText("4");
                    pages[5] = findViewById(R.id.btn5);
                    pages[6] = findViewById(R.id.btn6);

                    pages[7] = findViewById(R.id.btn7);
                    pages[8] = findViewById(R.id.btn8);

                    pages[8].setVisibility(View.GONE);
                    pages[6].setVisibility(View.GONE);
                    pages[7].setVisibility(View.GONE);
                    pages[5].setVisibility(View.GONE);
                    pages[4].setBackground(yes_pages);
                    pages[1].setBackground(no_pages);
                    pages[2].setBackground(no_pages);
                    pages[3].setBackground(no_pages);
                    pages[2].setTextColor(getResources().getColor(R.color.Black));
                    pages[3].setTextColor(getResources().getColor(R.color.Black));
                    pages[1].setTextColor(getResources().getColor(R.color.Black));
                    pages[4].setTextColor(getResources().getColor(R.color.White));
            }
        }
        if(lastpage == 3) {
            if(where == 0) {
                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.GONE);
                pages[1] = findViewById(R.id.btn1);
                pages[1].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[3].setText("3");
                pages[4] = findViewById(R.id.btn4);
                pages[5] = findViewById(R.id.btn5);
                pages[6] = findViewById(R.id.btn6);

                pages[7] = findViewById(R.id.btn7);
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);
                pages[7].setVisibility(View.GONE);
                pages[5].setVisibility(View.GONE);

                pages[1].setBackground(yes_pages);
                pages[2].setBackground(no_pages);
                pages[3].setBackground(no_pages);
                pages[3].setTextColor(getResources().getColor(R.color.Black));
                pages[2].setTextColor(getResources().getColor(R.color.Black));
                pages[1].setTextColor(getResources().getColor(R.color.White));
            }
            if(where == 1) {
                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.VISIBLE);
                pages[1] = findViewById(R.id.btn1);
                pages[0].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[3].setText("3");
                pages[4] = findViewById(R.id.btn4);
                pages[5] = findViewById(R.id.btn5);
                pages[6] = findViewById(R.id.btn6);

                pages[7] = findViewById(R.id.btn7);
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);
                pages[7].setVisibility(View.GONE);
                pages[5].setVisibility(View.GONE);

                pages[2].setBackground(yes_pages);
                pages[1].setBackground(no_pages);
                pages[3].setBackground(no_pages);
                pages[3].setTextColor(getResources().getColor(R.color.Black));
                pages[1].setTextColor(getResources().getColor(R.color.Black));
                pages[2].setTextColor(getResources().getColor(R.color.White));
            }
            if(where == 2) {
                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.VISIBLE);
                pages[1] = findViewById(R.id.btn1);
                pages[0].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[3].setText("3");
                pages[4] = findViewById(R.id.btn4);
                pages[5] = findViewById(R.id.btn5);
                pages[6] = findViewById(R.id.btn6);

                pages[7] = findViewById(R.id.btn7);
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.GONE);
                pages[4].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);
                pages[7].setVisibility(View.GONE);
                pages[5].setVisibility(View.GONE);


                pages[3].setBackground(yes_pages);
                pages[2].setBackground(no_pages);
                pages[1].setBackground(no_pages);
                pages[2].setTextColor(getResources().getColor(R.color.Black));
                pages[1].setTextColor(getResources().getColor(R.color.Black));
                pages[3].setTextColor(getResources().getColor(R.color.White));
            }
        }
        if(lastpage == 2) {
            if(where == 0) {
                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.GONE);
                pages[1] = findViewById(R.id.btn1);
                pages[1].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[4] = findViewById(R.id.btn4);
                pages[5] = findViewById(R.id.btn5);
                pages[6] = findViewById(R.id.btn6);

                pages[7] = findViewById(R.id.btn7);
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);
                pages[7].setVisibility(View.GONE);
                pages[5].setVisibility(View.GONE);
                pages[3].setVisibility(View.GONE);

                pages[1].setBackground(yes_pages);
                pages[2].setBackground(no_pages);
                pages[2].setTextColor(getResources().getColor(R.color.Black));
                pages[1].setTextColor(getResources().getColor(R.color.White));
            }
            if(where == 1) {
                pages[0] = findViewById(R.id.btn0);
                pages[0].setVisibility(View.VISIBLE);
                pages[1] = findViewById(R.id.btn1);
                pages[1].setText("1");
                pages[2] = findViewById(R.id.btn2);
                pages[2].setText("2");
                pages[3] = findViewById(R.id.btn3);
                pages[4] = findViewById(R.id.btn4);
                pages[5] = findViewById(R.id.btn5);
                pages[6] = findViewById(R.id.btn6);

                pages[7] = findViewById(R.id.btn7);
                pages[8] = findViewById(R.id.btn8);

                pages[8].setVisibility(View.GONE);
                pages[4].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);
                pages[7].setVisibility(View.GONE);
                pages[5].setVisibility(View.GONE);
                pages[3].setVisibility(View.GONE);

                pages[2].setBackground(yes_pages);
                pages[1].setBackground(no_pages);
                pages[1].setTextColor(getResources().getColor(R.color.Black));
                pages[2].setTextColor(getResources().getColor(R.color.White));
            }

        }
        if(lastpage == 1) {
            pages[0] = findViewById(R.id.btn0);
            pages[0].setVisibility(View.GONE);
            pages[1] = findViewById(R.id.btn1);
            pages[1].setText("1");
            pages[2] = findViewById(R.id.btn2);
            pages[3] = findViewById(R.id.btn3);
            pages[4] = findViewById(R.id.btn4);
            pages[5] = findViewById(R.id.btn5);
            pages[6] = findViewById(R.id.btn6);

            pages[7] = findViewById(R.id.btn7);
            pages[8] = findViewById(R.id.btn8);

            pages[8].setVisibility(View.GONE);
            pages[4].setVisibility(View.GONE);
            pages[6].setVisibility(View.GONE);
            pages[7].setVisibility(View.GONE);
            pages[5].setVisibility(View.GONE);
            pages[3].setVisibility(View.GONE);
            pages[2].setVisibility(View.GONE);
            pages[1].setTextColor(getResources().getColor(R.color.White));

            pages[1].setBackground(yes_pages);
        }
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {


        public MyFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragmentforReading.newInstance(position);
        }

        @Override
        public int getCount() {
            return lastpage;
        }

    }

        // set up the RecyclerView
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

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }





}