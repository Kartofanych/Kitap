package com.example.irekonelove4.ui.home;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.irekonelove4.Adapter_for_Home;
import com.example.irekonelove4.ApiClient;
import com.example.irekonelove4.DataRequest.BookForJson;
import com.example.irekonelove4.DataRequest.BookNotForJson;
import com.example.irekonelove4.DataRequest.FirstPrReq;
import com.example.irekonelove4.KnigaAbout;
import com.example.irekonelove4.Models.PageFragment;
import com.example.irekonelove4.PageFragmentforAccount;
import com.example.irekonelove4.PageFragmentforHome;
import com.example.irekonelove4.R;
import com.example.irekonelove4.ui.accounts.AccountFragment;
import com.google.gson.annotations.SerializedName;


import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    static final int PAGE_COUNT = 8;
    private ScrollView scrollView_inHome;
    private int where = 1;
    private URL url;
    private LayoutInflater infl;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_CITY = "City";
    public static final String APP_PREFERENCES_MAIL = "Mail";
    public static final String APP_PREFERENCES_PASSWORD = "Pass";
    public static final String APP_PREFERENCES_SEX = "00";
    public static final String APP_PREFERENCES_ID = "ID";
    public static final String APP_PREFERENCES_AVATAR = "AVATAR";
    public static final String APP_PREFERENCES_QUOTE = "Quote";
    public static final String APP_PREFERENCES_FAVOURITE = "Fav";
    public static final String APP_PREFERENCES_RECOMMEND_GENRES = "GENRES";
    public static final String APP_PREFERENCES_RECOMMEND_AUTHORS = "AUTHORS";
    public static final String APP_PREFERENCES_INTERVIEW = "Interview";
    public static final String APP_PREFERENCES_DATA = "Data";
    private String fullstring;
    private FirstPrReq loginResponse;
    private ViewPager popular, preferences;
    private PagerAdapter pagerAdapter;
    private List<BookNotForJson> models;
    private View view;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_home, container, false);
        models = new ArrayList<>();

        infl = getLayoutInflater();

        TextView tools_name = (TextView) view.findViewById(R.id.fragment_name);
        ConstraintLayout allin = (ConstraintLayout) view.findViewById(R.id.allIN);
        scrollView_inHome = (ScrollView) view.findViewById(R.id.allkarusel_in_home);

        popular = view.findViewById(R.id.scroll_pop);
        pagerAdapter = new HomeFragment.MyFragmentPagerAdapter(getFragmentManager());
        popular.setAdapter(pagerAdapter);


        //info();



        return view;
    }


    private void info(){
        Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();
        loginDataCall.enqueue(new Callback<FirstPrReq>() {
            @Override
            public void onResponse(Call<FirstPrReq> call, Response<FirstPrReq> response) {
                if (response.isSuccessful()) {
                    loginResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int last = 0;

                            for (int j = 0; j < 12; j++) {
                                if (j == 11) {
                                    break;
                                }
                                for (int i = last; i < loginResponse.getBooks().size(); i++) {
                                    if (Integer.parseInt(loginResponse.getBooks().get(i).getViews()) >= 10) {
                                        if (j < 11) {
                                            last = i + 1;

                                            String urll = loginResponse.getBooks().get(i).getImg();
                                            urll.trim().replaceAll(" ", "%20");
                                            String name, autor, about, genre, lang, img, views;
                                            name = loginResponse.getBooks().get(i).getName();
                                            autor = loginResponse.getBooks().get(i).getAuthor();
                                            about = loginResponse.getBooks().get(i).getBriefly();
                                            genre = loginResponse.getBooks().get(i).getGenre();
                                            lang = loginResponse.getBooks().get(i).getLanguage();
                                            views = loginResponse.getBooks().get(i).getViews();

                                                /*try {
                                                    Glide.with(getContext()).load("http://irek.studio/books/books_img/" + urll).into(imageView);
                                                } catch (Exception e) {

                                                }*/
                                            Log.d("1",loginResponse.getBooks().get(i).getName() );
                                            models.add(0, new BookNotForJson(name, autor,about,genre,lang,urll,views));


                                            j++;
                                        } else {
                                            String urll = loginResponse.getBooks().get(i).getImg();
                                            urll.trim().replaceAll(" ", "%20");
                                            String name, autor, about, genre, lang, img, views;
                                            name = loginResponse.getBooks().get(i).getName();
                                            autor = loginResponse.getBooks().get(i).getAuthor();
                                            about = loginResponse.getBooks().get(i).getBriefly();
                                            genre = loginResponse.getBooks().get(i).getGenre();
                                            lang = loginResponse.getBooks().get(i).getLanguage();
                                            views = loginResponse.getBooks().get(i).getViews();
                                            models.add(0, new BookNotForJson(name, autor,about,genre,lang,urll,views));

                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }, 1000);
                    new CountDownTimer(1100, 500) {

                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            Log.d("1",String.valueOf(models.get(5).author));
                            Adapter_for_Home adapter = new Adapter_for_Home(models, getContext());
                            popular.setAdapter(adapter);
                        }
                    }.start();


                }


            }

            @Override
            public void onFailure(Call<FirstPrReq> call, Throwable t) {

            }
        });

    }



    private int j = 0;

    public void AllInfooo(){
        final Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();
        infl = getLayoutInflater();

        loginDataCall.enqueue(new Callback<FirstPrReq>() {//////////////////////////popular Response!!!!!!!!
            @Override
            public void onResponse(Call<FirstPrReq> call, Response<FirstPrReq> response) {
                if (response.isSuccessful()) {
                    loginResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int last = 0;

                            for (int j = 0; j < 12; j++) {
                                if (j == 11) {
                                    break;
                                }
                                for (int i = last; i < loginResponse.getBooks().size(); i++) {
                                    if (Integer.parseInt(loginResponse.getBooks().get(i).getViews()) >= 10) {
                                        if (j < 11) {
                                            last = i + 1;
                                            View view = infl.inflate(R.layout.custom_kitap_in_home, null, false);
                                            view.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    bigClick(v);
                                                }
                                            });

                                            TextView name = view.findViewById(R.id.name);
                                            name.setText(loginResponse.getBooks().get(i).getName());
                                            TextView autor = view.findViewById(R.id.autor);
                                            autor.setText(loginResponse.getBooks().get(i).getAuthor());

                                            ImageView imageView = view.findViewById(R.id.KitapImage);

                                            String urll = loginResponse.getBooks().get(i).getImg();
                                            urll.trim().replaceAll(" ", "%20");

                                            try {
                                                Glide.with(getContext()).load("http://irek.studio/books/books_img/" + urll).into(imageView);
                                            } catch (Exception e) {

                                            }


                                           // popular.addView(view);

                                            j++;
                                        } else {

                                            last = i + 1;

                                            View view = infl.inflate(R.layout.customkitapforcarusel, null, false);
                                            view.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    bigClick(v);
                                                }
                                            });

                                            TextView name = view.findViewById(R.id.name);
                                            name.setText(loginResponse.getBooks().get(i).getName());
                                            TextView autor = view.findViewById(R.id.autor);
                                            autor.setText(loginResponse.getBooks().get(i).getAuthor());
                                            ImageView imageView = view.findViewById(R.id.KitapImage);

                                            String urll = loginResponse.getBooks().get(i).getImg();
                                            urll.trim().replaceAll(" ", "%20");
                                            try {
                                                Glide.with(getContext()).load("http://irek.studio/books/books_img/" + urll).into(imageView);
                                            } catch (Exception e) {

                                            }
                                            //popular.addView(view);

                                            break;
                                        }
                                    }
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


    public void bigClick(View view){

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
                                intent.putExtra("how_many", loginResponse.getBooks().get(finalI).getViews());
                                intent.putExtra("img", loginResponse.getBooks().get(finalI).getImg());
                                intent.putExtra("url", url.toString());
                                intent.putExtra("text", fullstring);
                                Log.d("1",loginResponse.getBooks().get(finalI).getLanguage()+" " +loginResponse.getBooks().get(finalI).getLanguageOriginal()
                                        + " " + loginResponse.getBooks().get(finalI).getTags()+ " " + loginResponse.getBooks().get(finalI).getSizeOfBookPages()
                                        + " "+loginResponse.getBooks().get(finalI).getUserAuthor()+ " " + loginResponse.getBooks().get(finalI).getViews());
                                startActivity(intent);
                            }
                        },200);
                    }
                }
            }
        }, 800);
    }

    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragmentforHome.newInstance(position);
        }

        @Override
        public int getCount() {
            return 6;
        }

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