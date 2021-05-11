package com.example.irekonelove4;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.irekonelove4.DataRequest.FirstPrReq;
import com.example.irekonelove4.ui.accounts.AccountFragment;
import com.example.irekonelove4.ui.home.HomeFragment;
import com.example.irekonelove4.ui.search.SearchFragment;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageFragmentforAccount extends Fragment {

    static final String TAG = "myLogs";

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String SAVE_PAGE_NUMBER = "save_page_number";

    int pageNumber;
    private FirstPrReq loginResponse;
    private LayoutInflater inflater;
    private LinearLayout lin1, lin2;
    private Button all;
    private String favourites;
    int[] fav;
    private LottieAnimationView lottie;
    private URL url;
    private String fullstring;
    private Animation anim;
    private RelativeLayout rel_anim;


    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_CITY = "City";
    public static final String APP_PREFERENCES_MAIL = "Mail";
    public static final String APP_PREFERENCES_ID = "ID";
    public static final String APP_PREFERENCES_PASSWORD = "Pass";
    public static final String APP_PREFERENCES_SEX = "00";
    public static final String APP_PREFERENCES_AVATAR = "AVATAR";
    public static final String APP_PREFERENCES_QUOTE = "Quote";
    public static final String APP_PREFERENCES_FAVOURITE = "Fav";
    public static final String APP_PREFERENCES_RECOMMEND_GENRES = "GENRES";
    public static final String APP_PREFERENCES_RECOMMEND_AUTHORS = "AUTHORS";
    public static final String APP_PREFERENCES_INTERVIEW = "Interview";
    public static final String APP_PREFERENCES_DATA = "Data";
    public SharedPreferences mSettings;
    public static PageFragmentforAccount newInstance(int page) {
        PageFragmentforAccount pageFragment = new PageFragmentforAccount();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        mSettings = getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        int savedPageNumber = -1;
        if (savedInstanceState != null) {
            savedPageNumber = savedInstanceState.getInt(SAVE_PAGE_NUMBER);
        }
        Log.d(TAG, "savedPageNumber = " + savedPageNumber);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if(pageNumber == 0) {
            view = inflater.inflate(R.layout.fragment_po_izbr, null);

            all = view.findViewById(R.id.all);
            lin1 = view.findViewById(R.id.lin);
            lin1.setVisibility(View.VISIBLE);
            lottie = view.findViewById(R.id.animation);
            rel_anim = view.findViewById(R.id.rel_for_anim);
            anim = AnimationUtils.loadAnimation(getContext(),R.anim.go_back);
            all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),AllOf.class);
                    intent.putExtra("tag","Все избранные");
                    startActivity(intent);
                }
            });
            Log.d("1", mSettings.getString(APP_PREFERENCES_AGE,"00"));
            if(!mSettings.getString(APP_PREFERENCES_AGE,"Age").equals("Age")) {
                AllInfoo(1);
            }

        }
        if(pageNumber == 1) {
            view = inflater.inflate(R.layout.fragment_po_dobav, null);

            all = view.findViewById(R.id.all_dobav);
            lin2 = view.findViewById(R.id.lin2);
            lin2.setVisibility(View.VISIBLE);
            lottie = view.findViewById(R.id.animation2);
            rel_anim = view.findViewById(R.id.rel_for_anim_dob);
            anim = AnimationUtils.loadAnimation(getContext(),R.anim.go_back);
            all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),AllOf.class);
                    intent.putExtra("tag","Все добавленные");
                    startActivity(intent);
                }
            });

        }


        return view;
    }

    public void AllInfoo(int state) {
        Log.d("2", "fuck");
        final Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();
        inflater = getLayoutInflater();
        if(state == 1) {
            loginDataCall.enqueue(new Callback<FirstPrReq>() {
                @Override
                public void onResponse(Call<FirstPrReq> call, Response<FirstPrReq> response) {
                    if (response.isSuccessful()) {
                        loginResponse = response.body();
                        new CountDownTimer(3000, 500) {

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

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                favourites = mSettings.getString(APP_PREFERENCES_FAVOURITE,"Favourite");
                                int count = 0;
                                for (int g = 0; g < favourites.length(); g++) {
                                    if (favourites.charAt(g) == ',') {
                                        count++;
                                    }
                                    else if(g == favourites.length()-1){
                                        count++;
                                    }
                                }
                                Log.d("1",favourites+ " " +String.valueOf(count));
                                fav = new int[count];
                                int s = 0;
                                StringBuilder el = new StringBuilder();
                                for (int g = 0; g < favourites.length(); g++) {
                                    if (favourites.charAt(g) == ',') {
                                        fav[s] = Integer.parseInt(el.toString());
                                        s++;
                                        el = new StringBuilder();
                                    }else if(g == favourites.length()-1){
                                        el.append(favourites.charAt(g));
                                        fav[s] = Integer.parseInt(el.toString());
                                        s++;
                                        el = new StringBuilder();
                                    }else {
                                        el.append(favourites.charAt(g));
                                    }
                                }

                                for (int j = 0; j < fav.length; j++) {
                                    /*if(j == 0){
                                        View view = inflater.inflate(R.layout.customkitapforcarusel,null);
                                        String urll = loginResponse.getBooks().get(fav[j]).getImg();
                                        ImageView imageView = view.findViewById(R.id.KitapImage);
                                        urll.trim().replaceAll(" ", "%20");
                                        Glide.with(getContext()).load("http://irek.studio/books/books_img/" + urll).into(imageView);
//                                        Image image = ImageIO.read(url);
                                        TextView name = view.findViewById(R.id.name);
                                        name.setText(loginResponse.getBooks().get(fav[j]).getName());
                                        TextView autor = view.findViewById(R.id.autor);
                                        autor.setText(loginResponse.getBooks().get(fav[j]).getAuthor());
                                        view.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                bigClick(v);
                                            }
                                        });
                                        lin1.addView(view);
                                    }*/
                                    if (j < fav.length - 1 ) {

                                        View view = inflater.inflate(R.layout.customkitapforcarusel,null);
                                        String urll = loginResponse.getBooks().get(fav[j]-1).getImg();
                                        ImageView imageView = view.findViewById(R.id.KitapImage);
                                        urll.trim().replaceAll(" ", "%20");
                                        Glide.with(getContext()).load("http://irek.studio/books/books_img/" + urll).into(imageView);
//                                        Image image = ImageIO.read(url);
                                        TextView name = view.findViewById(R.id.name);
                                        name.setText(loginResponse.getBooks().get(fav[j]-1).getName());
                                        TextView autor = view.findViewById(R.id.autor);
                                        autor.setText(loginResponse.getBooks().get(fav[j]-1).getAuthor());
                                        view.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                bigClick(v);
                                            }
                                        });
                                        lin1.addView(view);
                                        //ImageView imageView = viewPager1.findViewById(R.id.)
                                        Log.d(String.valueOf(j), String.valueOf(fav[j]-1));
                                    } else if(j == fav.length-1){

                                        View view = inflater.inflate(R.layout.customkitapforcarusel,null);
                                        String urll = loginResponse.getBooks().get(fav[j]-1).getImg();
                                        ImageView imageView = view.findViewById(R.id.KitapImage);
                                        urll.trim().replaceAll(" ", "%20");
                                        Glide.with(getContext()).load("http://irek.studio/books/books_img/" + urll).into(imageView);
//                                        Image image = ImageIO.read(url);
                                        TextView name = view.findViewById(R.id.name);
                                        name.setText(loginResponse.getBooks().get(fav[j]-1).getName());
                                        TextView autor = view.findViewById(R.id.autor);
                                        autor.setText(loginResponse.getBooks().get(fav[j]-1).getAuthor());
                                        view.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                bigClick(v);
                                            }
                                        });
                                        lin1.addView(view);
                                        Log.d(String.valueOf(j), String.valueOf(fav[j]));
                                        break;
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


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_PAGE_NUMBER, pageNumber);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + pageNumber);
    }
}