package com.example.irekonelove4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.irekonelove4.DataRequest.FirstPrReq;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllOf extends Activity {
    private LinearLayout lin1, lin2, lin3;
    private FirstPrReq loginResponse;
    private LayoutInflater inflater;
    private RelativeLayout rel_anim;
    private LottieAnimationView lottie;
    private Animation anim;
    private int k = 3;
    private String tag = "";
    private TextView tag_txt;


    private String favourites;
    int[] fav;


    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_CITY = "City";
    public static final String APP_PREFERENCES_MAIL = "Mail";
    public static final String APP_PREFERENCES_PASSWORD = "Pass";
    public static final String APP_PREFERENCES_ID = "ID";
    public static final String APP_PREFERENCES_SEX = "00";
    public static final String APP_PREFERENCES_AVATAR = "AVATAR";
    public static final String APP_PREFERENCES_QUOTE = "Quote";
    public static final String APP_PREFERENCES_FAVOURITE = "Fav";
    public static final String APP_PREFERENCES_RECOMMEND_GENRES = "GENRES";
    public static final String APP_PREFERENCES_RECOMMEND_AUTHORS = "AUTHORS";
    public static final String APP_PREFERENCES_INTERVIEW = "Interview";
    public static final String APP_PREFERENCES_DATA = "Data";
    public SharedPreferences mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_of);

        mSettings = this.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        anim = AnimationUtils.loadAnimation(this,R.anim.go_back);
        lin1 = findViewById(R.id.first);
        lin2 = findViewById(R.id.second);
        lin3 = findViewById(R.id.third);
        rel_anim = findViewById(R.id.rel_for_anim);
        lottie = findViewById(R.id.animation);
        tag_txt = findViewById(R.id.tag);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tag = extras.getString("tag");
            tag_txt.setText(tag);
        }
        if(!(tag.equals("Все избранные")||tag.equals("Все добавленные"))) {
            AllInfoGenres(tag);
        }else{
            AllInfoOf(tag);
        }
    }


    public void AllInfoOf(final String s) {
        if(s.equals("Все избранные")){
            final Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();
            inflater = getLayoutInflater();
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
                                    int k = 0;
                                    for (int j = 0; j < fav.length; j++) {

                                        if (k % 3 == 0) {
                                            if (j < fav.length - 1) {
                                                View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                                String urll = loginResponse.getBooks().get(fav[j]-1).getImg();
                                                ImageView imageView = view.findViewById(R.id.KitapImage);
                                                urll.trim().replaceAll(" ", "%20");
                                                Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
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
                                                Log.d("1", "gg");
                                            } else {

                                                View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                                String urll = loginResponse.getBooks().get(fav[j]-1).getImg();
                                                ImageView imageView = view.findViewById(R.id.KitapImage);
                                                urll.trim().replaceAll(" ", "%20");
                                                Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
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
                                                break;
                                            }
                                            k++;
                                        } else if (k % 3 == 1) {
                                            if (j < fav.length - 1) {
                                                View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                                String urll = loginResponse.getBooks().get(fav[j]-1).getImg();
                                                ImageView imageView = view.findViewById(R.id.KitapImage);
                                                urll.trim().replaceAll(" ", "%20");
                                                Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
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
                                                lin2.addView(view);
                                                //ImageView imageView = viewPager1.findViewById(R.id.)
                                                Log.d("1", "gg");
                                            } else {

                                                View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                                String urll = loginResponse.getBooks().get(fav[j]-1).getImg();
                                                ImageView imageView = view.findViewById(R.id.KitapImage);
                                                urll.trim().replaceAll(" ", "%20");
                                                Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
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
                                                lin2.addView(view);
                                                break;
                                            }
                                            k++;
                                        }else if (k % 3 == 2) {
                                            if (j < fav.length - 1) {
                                                View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                                String urll = loginResponse.getBooks().get(fav[j]-1).getImg();
                                                ImageView imageView = view.findViewById(R.id.KitapImage);
                                                urll.trim().replaceAll(" ", "%20");
                                                Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
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
                                                lin3.addView(view);
                                                //ImageView imageView = viewPager1.findViewById(R.id.)
                                                Log.d("1", "gg");
                                            } else{

                                                View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                                String urll = loginResponse.getBooks().get(fav[j]-1).getImg();
                                                ImageView imageView = view.findViewById(R.id.KitapImage);
                                                urll.trim().replaceAll(" ", "%20");
                                                Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
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
                                                lin3.addView(view);
                                                break;
                                            }
                                            k++;
                                        }
                                    }
                                }
                            }, 1000);


                        }
                    }

                    @Override
                    public void onFailure(Call<FirstPrReq> call, Throwable t) {
                        Log.e("Error", t.getLocalizedMessage());
                    }
                });
        } else{
            final Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();
            inflater = getLayoutInflater();

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
                                int k = 0;
                                for (int i = 0; i < loginResponse.getBooks().size(); i++) {
                                    //////////ЛОГИН ЮЗЕРА!!!!!!!!!!!!!!!!!!!!!
                                    if(k % 3 == 0) {
                                        if (loginResponse.getBooks().get(i).getUserAuthor().equals(mSettings.getString(APP_PREFERENCES_LOGIN, "Login"))) {
                                            View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                            String urll = loginResponse.getBooks().get(i).getImg();
                                            ImageView imageView = view.findViewById(R.id.KitapImage);
                                            urll.trim().replaceAll(" ", "%20");
                                            Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
//                                        Image image = ImageIO.read(url);
                                            TextView name = view.findViewById(R.id.name);
                                            name.setText(loginResponse.getBooks().get(i).getName());
                                            TextView autor = view.findViewById(R.id.autor);
                                            autor.setText(loginResponse.getBooks().get(i).getAuthor());
                                            view.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    bigClick(v);
                                                }
                                            });


                                            lin1.addView(view);
                                            k++;
                                        }
                                    }else if(k%3 == 1){
                                        View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                        String urll = loginResponse.getBooks().get(i).getImg();
                                        ImageView imageView = view.findViewById(R.id.KitapImage);
                                        urll.trim().replaceAll(" ", "%20");
                                        Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
//                                        Image image = ImageIO.read(url);
                                        TextView name = view.findViewById(R.id.name);
                                        name.setText(loginResponse.getBooks().get(i).getName());
                                        TextView autor = view.findViewById(R.id.autor);
                                        autor.setText(loginResponse.getBooks().get(i).getAuthor());
                                        view.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                bigClick(v);
                                            }
                                        });


                                        lin2.addView(view);
                                        k++;
                                    }else if(k%3 == 2){
                                        View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                        String urll = loginResponse.getBooks().get(i).getImg();
                                        ImageView imageView = view.findViewById(R.id.KitapImage);
                                        urll.trim().replaceAll(" ", "%20");
                                        Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
//                                        Image image = ImageIO.read(url);
                                        TextView name = view.findViewById(R.id.name);
                                        name.setText(loginResponse.getBooks().get(i).getName());
                                        TextView autor = view.findViewById(R.id.autor);
                                        autor.setText(loginResponse.getBooks().get(i).getAuthor());
                                        view.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                bigClick(v);
                                            }
                                        });


                                        lin3.addView(view);
                                        k++;
                                    }
                                }
                            }
                        }, 1000);


                    }
                }

                @Override
                public void onFailure(Call<FirstPrReq> call, Throwable t) {
                    Toast.makeText(AllOf.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Error", t.getLocalizedMessage());
                }
            });


        }


    }

    public void AllInfoGenres(final String s){
        final Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();
        inflater = getLayoutInflater();

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
                                for (int i = 1; i <= loginResponse.getBooks().size(); i++) {

                                    if (loginResponse.getBooks().get(i-1).getGenre().equals(s)) {
                                        Log.d("1",String.valueOf(i-1));
                                        if (k % 3 == 0) {
                                            View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                            String urll = loginResponse.getBooks().get(i-1).getImg();
                                            ImageView imageView = view.findViewById(R.id.KitapImage);
                                            urll.trim().replaceAll(" ", "%20");
                                            Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
//                                        Image image = ImageIO.read(url);
                                            TextView name = view.findViewById(R.id.name);
                                            name.setText(loginResponse.getBooks().get(i-1).getName());
                                            TextView autor = view.findViewById(R.id.autor);
                                            autor.setText(loginResponse.getBooks().get(i-1).getAuthor());
                                            view.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    bigClick(v);
                                                }
                                            });
                                            lin1.addView(view);
                                        } else if (k % 3 == 1) {
                                            View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                            String urll = loginResponse.getBooks().get(i-1).getImg();
                                            ImageView imageView = view.findViewById(R.id.KitapImage);
                                            urll.trim().replaceAll(" ", "%20");
                                            Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
//                                        Image image = ImageIO.read(url);
                                            TextView name = view.findViewById(R.id.name);
                                            name.setText(loginResponse.getBooks().get(i-1).getName());
                                            TextView autor = view.findViewById(R.id.autor);
                                            autor.setText(loginResponse.getBooks().get(i-1).getAuthor());
                                            view.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    bigClick(v);
                                                }
                                            });
                                            lin2.addView(view);
                                        } else if (k % 3 == 2) {
                                            View view = inflater.inflate(R.layout.customkitapforcarusel, null);
                                            String urll = loginResponse.getBooks().get(i-1).getImg();
                                            ImageView imageView = view.findViewById(R.id.KitapImage);
                                            urll.trim().replaceAll(" ", "%20");
                                            Glide.with(AllOf.this).load("http://irek.studio/books/books_img/" + urll).into(imageView);
//                                        Image image = ImageIO.read(url);
                                            TextView name = view.findViewById(R.id.name);
                                            name.setText(loginResponse.getBooks().get(i-1).getName());
                                            TextView autor = view.findViewById(R.id.autor);
                                            autor.setText(loginResponse.getBooks().get(i-1).getAuthor());
                                            view.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    bigClick(v);
                                                }
                                            });
                                            lin3.addView(view);
                                        }


                                        k++;
                                    }
                                }
                            }
                    }, 1000);


                } else {
                    Toast.makeText(AllOf.this, "Неправильный логин или пароль!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FirstPrReq> call, Throwable t) {
                Toast.makeText(AllOf.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                        /*if(loginResponse.getBooks().get(i).getLanguage().equals("Татарский")) {
                            try {
                                url = new URL("http://irek.studio/books/books_text/books_txt/" + loginResponse.getBooks().get(i).getName().trim().replaceAll(" ","%20") + "_tat.txt");
                                new AsyncRequest().execute("123", "/ajax", "foo=bar");
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }*/

                        Timer timer = new Timer();
                        final int finalI = i;
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                String fullstring = "";
                                Intent intent = new Intent(AllOf.this, KnigaAbout.class);
                                intent.putExtra("name",name.getText().toString());
                                intent.putExtra("autor",autor.getText().toString());
                                intent.putExtra("lang", loginResponse.getBooks().get(finalI).getLanguage());
                                intent.putExtra("genre", loginResponse.getBooks().get(finalI).getGenre());
                                intent.putExtra("lang1", loginResponse.getBooks().get(finalI).getLanguageOriginal());
                                intent.putExtra("tag", loginResponse.getBooks().get(finalI).getTags());
                                intent.putExtra("kol_str", loginResponse.getBooks().get(finalI).getSizeOfBookPages());
                                intent.putExtra("who", loginResponse.getBooks().get(finalI).getUserAuthor());
                                intent.putExtra("how_many", loginResponse.getBooks().get(finalI).getViews());
                                intent.putExtra("about", loginResponse.getBooks().get(finalI).getBriefly());
                                intent.putExtra("img", loginResponse.getBooks().get(finalI).getImg());
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


}