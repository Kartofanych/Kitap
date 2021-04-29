package com.example.irekonelove4;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.irekonelove4.Models.PageSplitter;
import com.example.irekonelove4.Models.TextPagerAdapter;

public class Next_Page2 extends AppCompatActivity{
    private ViewPager pager;
    private int where;
    private String lastpage;
    private Button[] pages;
    private Drawable no_pages;
    private Drawable yes_pages;
    private RelativeLayout menu;
    private int last = 9, ch = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);
        Bundle bundle = getIntent().getExtras();
        final String fulltext = bundle.getString("text");
        pager = (ViewPager) findViewById(R.id.pages);
        menu = findViewById(R.id.menu);
        no_pages = getResources().getDrawable(R.drawable.designfor_nopages_but);
        yes_pages = getResources().getDrawable(R.drawable.designfor_pages_but);

        // to get ViewPager width and height we have to wait global layout
        pager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                PageSplitter pageSplitter = new PageSplitter(pager.getWidth(), pager.getHeight()-30, 1, 0);

                    TextPaint textPaint = new TextPaint();
                    textPaint.setTextSize(getResources().getDimension(R.dimen.text_size));
                    pageSplitter.append(fulltext, textPaint);

                pager.setAdapter(new TextPagerAdapter(getSupportFragmentManager(), pageSplitter.getPages()));
                last = pageSplitter.getPages().size();
                lastpage = String.valueOf(last);


                pages = new Button[9];

                if(last>=6) {
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
                }
                if(last == 5) {
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
                if(last == 4) {
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
                if(last == 3) {
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
                if(last == 2) {
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
                if(last == 1) {
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
                pager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        Log.d("1",String.valueOf(lastpage));



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

        //menu.setOnTouchListener(this);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("1", "where");
                if (ch % 2 == 0) {
                    menu.setVisibility(View.GONE);
                } else {
                    menu.setVisibility(View.VISIBLE);
                }
                ch++;
                break;
        }
        return true;
    }

    private void FuckIt(Integer where) {
        Log.d("1", String.valueOf(where));

        pager.setCurrentItem(where);
        //text = pager.findViewById(R.id.text);
//        text.setText(fulltext.substring(1540*(where), 1540*(where+1)));
        if(last>=6) {
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
                pages[7].setText(lastpage);
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
                pages[7].setText(lastpage);
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
                pages[7].setText(lastpage);
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
                pages[7].setText(lastpage);
            } else if (where == last - 1) {
                pages[8].setVisibility(View.GONE);
                pages[7].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);
                pages[5].setVisibility(View.GONE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.VISIBLE);
                pages[0].setVisibility(View.VISIBLE);
                pages[4].setText(lastpage);
                pages[3].setText(String.valueOf(last - 1));
                pages[2].setText("...");
                pages[1].setText("1");

            } else if (where == last - 2) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.GONE);
                pages[6].setVisibility(View.GONE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.VISIBLE);
                pages[0].setVisibility(View.VISIBLE);

                pages[5].setText(lastpage);
                pages[4].setText(String.valueOf(last - 1));
                pages[3].setText(String.valueOf(last - 2));
                pages[2].setText("...");
                pages[1].setText("1");
            } else if (where == last - 3) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.GONE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.VISIBLE);
                pages[0].setVisibility(View.VISIBLE);

                pages[7].setText(lastpage);
                pages[5].setText(String.valueOf(last - 1));
                pages[4].setText(String.valueOf(last - 2));
                pages[3].setText(String.valueOf(last - 3));
                pages[2].setText("...");
                pages[1].setText("1");
            } else if (where == last - 4) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.VISIBLE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.VISIBLE);
                pages[0].setVisibility(View.VISIBLE);

                pages[7].setText(lastpage);
                pages[6].setText(String.valueOf(last - 1));
                pages[5].setText(String.valueOf(last - 2));
                pages[4].setText(String.valueOf(last - 3));
                pages[3].setText(String.valueOf(last - 4));
                pages[2].setText("...");
                pages[1].setText("1");
            } else if (where < last - 4 || where > 3) {
                pages[8].setVisibility(View.VISIBLE);
                pages[7].setVisibility(View.VISIBLE);
                pages[6].setVisibility(View.VISIBLE);
                pages[5].setVisibility(View.VISIBLE);
                pages[4].setVisibility(View.VISIBLE);
                pages[3].setVisibility(View.VISIBLE);
                pages[2].setVisibility(View.VISIBLE);
                pages[1].setVisibility(View.VISIBLE);
                pages[0].setVisibility(View.VISIBLE);

                pages[7].setText(lastpage);
                pages[6].setText("...");
                pages[5].setText(String.valueOf(where + 2));
                pages[4].setText(String.valueOf(where + 1));
                pages[3].setText(String.valueOf(where));
                pages[2].setText("...");
                pages[1].setText("1");
            }
        }

        if(last == 5) {
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
        if(last == 4) {
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
        if(last == 3) {
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
        if(last == 2) {
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
        if(last == 1) {
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

    /*@Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                pager.setClickable(false);
                break;
            case MotionEvent.ACTION_MOVE: // нажатие
                pager.setClickable(false);
                break;
            case MotionEvent.ACTION_UP:
                pager.setClickable(true);
                break;// отпускание
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }*/
}