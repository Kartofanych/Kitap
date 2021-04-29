package com.example.irekonelove4.ui.library;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.irekonelove4.AllOf;
import com.example.irekonelove4.ApiClient;
import com.example.irekonelove4.Enterance;
import com.example.irekonelove4.KnigaAbout;
import com.example.irekonelove4.R;
import com.example.irekonelove4.DataRequest.FirstPrReq;
import com.roacult.backdrop.BackdropLayout;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryFragment extends Fragment {

    private String[] mGroupsArray = new String[] { "Жанры"};

    private String[] genres = new String[] { "Сказки", "Рассказы", "Повести", "Романы",
            "Поэзия" };
    private int whereu = 1;
    float widthHor = 0, widthVert = 0;
    boolean yes = false;
    private int last_scroll_x = 0, last_scroll_y= 0, first = 0;
    private Animation anim, anim1;
    private String[] all_genres = {"Рассказы","Сказки","Повести","Зарубежная литература","Военная литература","Поэзия","Мысли","Статьи","Новеллы","Фантастика","Романы"
    };
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_CITY = "City";
    public static final String APP_PREFERENCES_MAIL = "Mail";
    public static final String APP_PREFERENCES_PASSWORD = "Pass";
    public static final String APP_PREFERENCES_SEX = "00";
    public static final String APP_PREFERENCES_AVATAR = "AVATAR";
    public static final String APP_PREFERENCES_QUOTE = "Quote";
    public static final String APP_PREFERENCES_FAVOURITE = "Fav";
    public static final String APP_PREFERENCES_RECOMMEND_GENRES = "GENRES";
    public static final String APP_PREFERENCES_RECOMMEND_AUTHORS = "AUTHORS";
    public static final String APP_PREFERENCES_INTERVIEW = "Interview";
    public static final String APP_PREFERENCES_DATA = "Data";

    private FirstPrReq loginResponse;
    public SharedPreferences mSettings;
    private URL url;
    private String fullstring = "";
    private HorizontalScrollView scrollView;
    private ScrollView bigScrollView;
    private RelativeLayout rel_anim;
    private LottieAnimationView lottie;
    private BackdropLayout dropIt;
    private View backend, frontend;
    private LinearLayout allback;
    private LayoutInflater inflaterr;
    private TextView[] genres_to_click = new TextView[11];
    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_library, container, false);
        anim = AnimationUtils.loadAnimation(getContext(), R.anim.go_back);
        anim1 = AnimationUtils.loadAnimation(getContext(), R.anim.go_follow);

        dropIt = view.findViewById(R.id.backDrop);

        allback = view.findViewById(R.id.allback);
        allback.setVisibility(View.GONE);
        backend = dropIt.getChildAt(0);
        frontend = dropIt.getChildAt(1);
        inflaterr = getLayoutInflater();

        mSettings = getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        //FirstPrReq firstPrReq = mSettings.getString(APP_PREFERENCES_DATA,"Data");
        TextView tools_name = (TextView) view.findViewById(R.id.fragment_name);
        tools_name.setText("Библиотека");
        rel_anim = view.findViewById(R.id.rel_for_anim);
        lottie = view.findViewById(R.id.animation);
        bigScrollView = (ScrollView) view.findViewById(R.id.bigScroll);
        bigScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        genres_to_click[0] = view.findViewById(R.id.first);
        genres_to_click[1] = view.findViewById(R.id.second);
        genres_to_click[2] = view.findViewById(R.id.third);
        genres_to_click[3] = view.findViewById(R.id.fourth);
        genres_to_click[4] = view.findViewById(R.id.fifth);
        genres_to_click[5] = view.findViewById(R.id.sixth);
        genres_to_click[6] = view.findViewById(R.id.seventh);
        genres_to_click[7] = view.findViewById(R.id.eightth);
        genres_to_click[8] = view.findViewById(R.id.ninth);
        genres_to_click[9] = view.findViewById(R.id.tenth);
        genres_to_click[10] = view.findViewById(R.id.eleventh);
//        for(int i = 0; i < )
        //AllInfoo();

        final Drawable scrl_for_gen1 = getResources().getDrawable(R.drawable.scroll_for_genres_1);
        final Drawable tch1 = getResources().getDrawable(R.drawable.tochka1);
        final Drawable scrl_for_gen2 = getResources().getDrawable(R.drawable.scroll_for_genres_2);
        final Drawable tch2 = getResources().getDrawable(R.drawable.tochka2);

        for (int i = 0; i < all_genres.length; i++) {
            final String genre_name = all_genres[i];
            LinearLayout.LayoutParams layoutParamss = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParamss.setMargins(40, 40, 40, 0);
            LinearLayout lay = (LinearLayout) inflaterr.inflate(R.layout.default_genre, null, true);
            TextView all_genre_item = lay.findViewById(R.id.all_genre_name);
            all_genre_item.setText("Все " + genre_name);
            all_genre_item.setTag(genre_name);
            all_genre_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    All(v);
                }
            });
            TextView genr = lay.findViewById(R.id.genre);
            genr.setText(genre_name);
            lay.setLayoutParams(layoutParamss);
            Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();
            final LinearLayout kon = lay.findViewById(R.id.lin_for_knigas);


            loginDataCall.enqueue(new Callback<FirstPrReq>() {
                @Override
                public void onResponse(Call<FirstPrReq> call, Response<FirstPrReq> response) {
                    if (response.isSuccessful()) {
                        loginResponse = response.body();
                        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        if (genre_name.equals("Рассказы")) {

                            new CountDownTimer(3000, 500) {

                                public void onTick(long millisUntilFinished) {
                                    if (millisUntilFinished < 1000) {
                                        allback.setVisibility(View.VISIBLE);
                                    }
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
                                            bigScrollView.setOnTouchListener(null);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                            }.start();
                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int last = 0;

                                for (int j = 0; j < 12; j++) {
                                    if (j == 11) {
                                        break;
                                    }
                                    for (int i = last; i < loginResponse.getBooks().size(); i++) {
                                        if (loginResponse.getBooks().get(i).getGenre().equals(genre_name)) {
                                            if (j < 11) {
                                                last = i + 1;

                                                layoutParams.setMargins(20, 20, 20, 20);
                                                layoutParams.gravity = Gravity.CENTER;
                                                View view = inflaterr.inflate(R.layout.customkitapforcarusel, null, false);
                                                view.setLayoutParams(layoutParams);
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


                                                kon.addView(view);

                                                j++;
                                            } else {

                                                last = i + 1;

                                                layoutParams.setMargins(20, 20, 20, 20);
                                                layoutParams.gravity = Gravity.CENTER;
                                                View view = inflaterr.inflate(R.layout.customkitapforcarusel, null, false);
                                                view.setLayoutParams(layoutParams);
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
                                                kon.addView(view);

                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }, 1000);


                    }


                }

                @Override
                public void onFailure(Call<FirstPrReq> call, Throwable t) {

                }
            });

            allback.addView(lay);
            Log.d("where", String.valueOf(lay.getBottom()));

        }

        for(int i = 0; i < 11; i ++){
            final int finalI = i;
            genres_to_click[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Genre_Click(genres_to_click[finalI].getText().toString());
                    //bigScrollView.requestChildFocus(allback.getChildAt(finalI),bigScrollView.getFocusedChild());
                }
            });
        }

        return view;
    }
    public void Genre_Click(final String genre){
        for(int index = 0; index < allback.getChildCount(); index++) {
            View nextChild = allback.getChildAt(index);
            TextView genre_in_click = nextChild.findViewById(R.id.genre);
            TextView all_genres_in_click = nextChild.findViewById(R.id.all_genre_name);
            if(genre_in_click.getText().toString().equals(genre)){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    bigScrollView.scrollToDescendant(all_genres_in_click);
                }
                //bigScrollView.requestChildFocus(nextChild,bigScrollView.getFocusedChild());
            }
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
                Log.d("1",fullstring);
                reader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public void All(View view){

        Intent intent = new Intent(getContext(), AllOf.class);
        intent.putExtra("tag",view.getTag().toString());
        startActivity(intent);


    }










}