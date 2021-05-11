package com.example.irekonelove4.ui.search;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.irekonelove4.ApiClient;
import com.example.irekonelove4.DataRequest.FirstPrReq;
import com.example.irekonelove4.KnigaAbout;
import com.example.irekonelove4.PageFragmentforSearch;
import com.example.irekonelove4.R;
import com.example.irekonelove4.ui.library.LibraryFragment;

import org.w3c.dom.Text;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    static final int PAGE_COUNT = 3;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private FirstPrReq loginResponse;
    private LinearLayout search;
    private TextView aut, kn, teg;
    private Drawable text_line_1,text_line;
    private int state = 1;
    private EditText poisk;
    private LayoutInflater inflater;
    private int where = 1;
    private URL url;
    private String fullstring;
    private ImageButton search_but;
    private RelativeLayout rel_anim;
    private LottieAnimationView lottie;
    private Animation anim, anim0;

    Bundle bundle = null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        anim0 = AnimationUtils.loadAnimation(getContext(),R.anim.go_follow);
        anim = AnimationUtils.loadAnimation(getContext(),R.anim.go_back);
        rel_anim = view.findViewById(R.id.rel_for_anim);
        lottie = view.findViewById(R.id.animation);
        poisk = view.findViewById(R.id.query_edit_text);
        text_line = getResources().getDrawable(R.drawable.text_line);
        text_line_1 = getResources().getDrawable(R.drawable.text_line_1);
        aut = view.findViewById(R.id.po_autoram);
        kn = view.findViewById(R.id.po_knigam);
        teg = view.findViewById(R.id.po_tegam);
        pager = (ViewPager) view.findViewById(R.id.pager);
        search_but = view.findViewById(R.id.search_but);
        pagerAdapter = new MyFragmentPagerAdapter(getFragmentManager());
        pager.setAdapter(pagerAdapter);




        search_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rel_anim.setVisibility(View.VISIBLE);
                rel_anim.startAnimation(anim0);
                new CountDownTimer(2000, 500) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        rel_anim.startAnimation(anim);
                        lottie.startAnimation(anim);
                        anim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                rel_anim.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }
                }.start();
                if(state == 1) {
                    search = pager.findViewById(R.id.search_aut);
                }else
                if(state == 2) {
                    search = pager.findViewById(R.id.search_kn);
                }else
                if(state == 3) {
                    search = pager.findViewById(R.id.search_teg);
                }
                if(search.getChildCount()!=0) {
                    search.removeAllViews();
                }
                AllInfoo(state);
            }
        });

        aut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aut.setBackground(text_line);
                kn.setBackground(text_line_1);
                teg.setBackground(text_line_1);
                pager.setCurrentItem(0);
            }
        });
        kn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kn.setBackground(text_line);
                aut.setBackground(text_line_1);
                teg.setBackground(text_line_1);
                pager.setCurrentItem(1);
            }
        });
        teg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teg.setBackground(text_line);
                kn.setBackground(text_line_1);
                aut.setBackground(text_line_1);
                pager.setCurrentItem(2);
            }
        });
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d("1", String.valueOf(position));
                    if(position == 0){

                        aut.setBackground(text_line);
                        kn.setBackground(text_line_1);
                        teg.setBackground(text_line_1);
                        state = 1;

                    }
                    else
                if(position == 1){

                    kn.setBackground(text_line);
                    aut.setBackground(text_line_1);
                    teg.setBackground(text_line_1);
                    state = 2;


                }else
                if(position == 2){

                    teg.setBackground(text_line);
                    kn.setBackground(text_line_1);
                    aut.setBackground(text_line_1);
                    state = 3;




                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragmentforSearch.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }


    public void AllInfoo(int state) {
        if(state == 1) {

            final Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();
            final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(20,20,20,0);

            inflater = getLayoutInflater();
            loginDataCall.enqueue(new Callback<FirstPrReq>() {
                @Override
                public void onResponse(Call<FirstPrReq> call, Response<FirstPrReq> response) {
                    if (response.isSuccessful()) {

                        loginResponse = response.body();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int last = 0;
                                for (int i = last; i < loginResponse.getBooks().size(); i++) {
                                    if(poisk.getText().toString().length()<2){
                                        //Toast.makeText(getContext(),"Слишком короткий ввод!",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    if (loginResponse.getBooks().get(i).getAuthor().contains(poisk.getText().toString())) {

                                        search = pager.findViewById(R.id.search_aut);
                                        View view = inflater.inflate(R.layout.custom_kitap_in_search, null);
                                        String urll = loginResponse.getBooks().get(i).getImg();
                                        ImageView imageView = view.findViewById(R.id.KitapImage);
                                        urll.trim().replaceAll(" ", "%20");
                                        try {
                                            Glide.with(getContext()).load("http://irek.studio/books/books_img/" + urll).into(imageView);
                                        }catch (Exception e){

                                        }
//                                        Image image = ImageIO.read(url);
                                        TextView name = view.findViewById(R.id.name);
                                        name.setText(loginResponse.getBooks().get(i).getName());
                                        TextView autor = view.findViewById(R.id.autor);
                                        autor.setText(loginResponse.getBooks().get(i).getAuthor());
                                        TextView genre = view.findViewById(R.id.genre_item);
                                        genre.setText(loginResponse.getBooks().get(i).getGenre());
                                        TextView lang = view.findViewById(R.id.language_item);
                                        lang.setText(loginResponse.getBooks().get(i).getLanguage());
                                        view.setLayoutParams(layoutParams);
                                        view.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                BigClick(v);
                                            }
                                        });
                                        search.addView(view);
                                    }
                                }
                            }
                        }, 1000);


                    } else {
                        Toast.makeText(getContext(), "Неправильный логин или пароль!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<FirstPrReq> call, Throwable t) {
                    Toast.makeText(getContext(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Error", t.getLocalizedMessage());
                }
            });
        }
        if(state == 2) {
            final Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();
            final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(20,20,20,0);

            inflater = getLayoutInflater();
            loginDataCall.enqueue(new Callback<FirstPrReq>() {
                @Override
                public void onResponse(Call<FirstPrReq> call, Response<FirstPrReq> response) {
                    if (response.isSuccessful()) {
                        loginResponse = response.body();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int last = 0;
                                for (int i = last; i < loginResponse.getBooks().size(); i++) {
                                    if(poisk.getText().toString().length()<2){
                                        //Toast.makeText(getContext(),"Слишком короткий ввод!",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    if (loginResponse.getBooks().get(i).getName().contains(poisk.getText().toString())) {

                                        search = pager.findViewById(R.id.search_kn);
                                        View view = inflater.inflate(R.layout.custom_kitap_in_search, null);
                                        String urll = loginResponse.getBooks().get(i).getImg();
                                        ImageView imageView = view.findViewById(R.id.KitapImage);
                                        urll.trim().replaceAll(" ", "%20");
                                        try {
                                            Glide.with(getContext()).load("http://irek.studio/books/books_img/" + urll).into(imageView);
                                        }catch (Exception e){

                                        }//                                        Image image = ImageIO.read(url);
                                        TextView name = view.findViewById(R.id.name);
                                        name.setText(loginResponse.getBooks().get(i).getName());
                                        TextView autor = view.findViewById(R.id.autor);
                                        autor.setText(loginResponse.getBooks().get(i).getAuthor());
                                        TextView genre = view.findViewById(R.id.genre_item);
                                        genre.setText(loginResponse.getBooks().get(i).getGenre());
                                        TextView lang = view.findViewById(R.id.language_item);
                                        lang.setText(loginResponse.getBooks().get(i).getLanguage());
                                        view.setLayoutParams(layoutParams);
                                        view.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                BigClick(v);
                                            }
                                        });
                                        search.addView(view);
                                        Log.d("more", "moree");
                                    }
                                }
                            }
                        }, 1000);


                    } else {
                        Toast.makeText(getContext(), "Неправильный логин или пароль!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<FirstPrReq> call, Throwable t) {
                    Toast.makeText(getContext(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Error", t.getLocalizedMessage());
                }
            });
        }
    }


    public void BigClick(View view){

        final TextView name = view.findViewById(R.id.name);
        final TextView autor = view.findViewById(R.id.autor);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int last = 0;
                for(int i = 0; i < loginResponse.getBooks().size(); i++){
                    if(loginResponse.getBooks().get(i).getName().equals(name.getText().toString())){

                        try {
                            url = new URL("http://irek.studio/books/books_text/books_txt/" + loginResponse.getBooks().get(i).getName().trim().replaceAll(" ","%20")+"_tat.txt");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        new AsyncRequest().execute("123", "/ajax", "foo=b'ar");

                        Timer timer = new Timer();
                        final int finalI = i;
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getContext(), KnigaAbout.class);
                                intent.putExtra("name",name.getText().toString());
                                intent.putExtra("autor",autor.getText().toString());
                                intent.putExtra("lang", loginResponse.getBooks().get(finalI).getLanguage());
                                intent.putExtra("genre", loginResponse.getBooks().get(finalI).getGenre());
                                intent.putExtra("lang1", loginResponse.getBooks().get(finalI).getLanguageOriginal());
                                intent.putExtra("tag", loginResponse.getBooks().get(finalI).getTags());
                                intent.putExtra("kol_str", loginResponse.getBooks().get(finalI).getSizeOfBookPages());
                                intent.putExtra("who", loginResponse.getBooks().get(finalI).getUserAuthor());
                                intent.putExtra("about", loginResponse.getBooks().get(finalI).getBriefly());
                                intent.putExtra("how_many", loginResponse.getBooks().get(finalI).getViews());
                                intent.putExtra("img", loginResponse.getBooks().get(finalI).getImg());
                                intent.putExtra("url", url.toString());
                                intent.putExtra("text", fullstring);
                                Log.d("1",loginResponse.getBooks().get(finalI).getLanguage()+" " +loginResponse.getBooks().get(finalI).getLanguageOriginal()
                                        + " " + loginResponse.getBooks().get(finalI).getTags()+ " " + loginResponse.getBooks().get(finalI).getSizeOfBookPages()
                                        + " "+loginResponse.getBooks().get(finalI).getUserAuthor()+ " " + loginResponse.getBooks().get(finalI).getViews());
                                startActivity(intent);
                            }
                        },1000);
                    }
                }
            }
        }, 1000);
    }

    public class AsyncRequest extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                String string;


                LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                string = reader.readLine();
                fullstring = string;
                int  i = 1;
                while (string != null) {
                    i++;
                    //System.out.println(string);
                    string = reader.readLine();
                    fullstring = fullstring + string;
                    Log.d(String.valueOf(i), fullstring);
                }
                // Log.d("1",fullstring);
                reader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }



}