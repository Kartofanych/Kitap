package com.example.irekonelove4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.irekonelove4.DataRequest.UserForJson;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Enterance extends Activity {


    public static void buttonEffect(View button) {
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(Color.parseColor("#ffa31a"), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_ID = "ID";
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


    public SharedPreferences mSettings;



    private Button ent;
    private TextView reg,nope;
    private RelativeLayout rel;

    static final private int CHOOSE_THIEF = 0;
    private EditText login, pass;
    int fuck = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterance);
        Log.d("1", "wtf");


        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        //Connect();
        //new AsyncRequest().execute("123", "/ajax", "foo=b'ar");
        rel = findViewById(R.id.rel_for_anim);
        login = findViewById(R.id.login);
        pass = findViewById(R.id.password);
        nope = findViewById(R.id.nope);
        ent = (Button) findViewById(R.id.entbut);
        ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("2", "1fuck");
                new AsyncRequest().execute("123", "/ajax", "foo=bar");
                rel.setVisibility(View.VISIBLE);
                nope.setVisibility(View.VISIBLE);
            }
        });
        reg = (TextView) findViewById(R.id.registration);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionIntent = new Intent(Enterance.this,
                        Registration.class);
                startActivityForResult(questionIntent, CHOOSE_THIEF);
                overridePendingTransition(R.anim.right_anim, R.anim.alpha_to_zero);
                /*Intent intent = new Intent(Enterance.this, Registration.class);
                startActivity(intent);*/

            }
        });
        buttonEffect(ent);

    }

    public void Login(){
       /* LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLogin(login.getText().toString());
        loginResponse.setPassword(pass.getText().toString());

        Call<UserForJson> call = ApiClient.getUserService().login(loginResponse);
        call.enqueue(new Callback<UserForJson>() {
            @Override
            public void onResponse(Call<UserForJson> call, Response<UserForJson> response) {

                Log.d("nice", "nicee");
                final UserForJson userForJson = response.body();
                if (response.isSuccessful()) {
                    Log.d("1", String.valueOf(response.body()));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putString(APP_PREFERENCES_NAME, userForJson.getName());
                            editor.putString(APP_PREFERENCES_AGE, userForJson.getDate());
                            editor.putString(APP_PREFERENCES_CITY, userForJson.getCity());
                            editor.putString(APP_PREFERENCES_LOGIN, userForJson.getLogin());
                            editor.putString(APP_PREFERENCES_PASSWORD, userForJson.getPassword());
                            editor.putString(APP_PREFERENCES_MAIL, userForJson.getEmail());
                            editor.apply();
                            Log.d("nice", "nice");

                            Intent questionIntent = new Intent(Enterance.this,
                                    MainActivity.class);
                            startActivityForResult(questionIntent, CHOOSE_THIEF);
                            overridePendingTransition(R.anim.down_anim, R.anim.alpha);
                        }
                    }, 4000);
                }
                else{
                    Log.d("1", "no");
                }

            }

            @Override
            public void onFailure(Call<UserForJson> call, Throwable t) {
                        Log.d("nooo", t.toString());
            }
        });*/



    }
    int a = 0;
    public class AsyncRequest extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {

                String logins = login.getText().toString();
                String passs = pass.getText().toString();
                String url = "http://irek.studio/api/login.php"+"?login=" + logins + "&password=" + passs;

                URL obj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

                connection.setRequestMethod("GET");


                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                final StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                    Log.d("String", response.toString());
                    Gson g = new Gson();
                    UserForJson userForJson = g.fromJson(response.toString(), UserForJson.class);
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString(APP_PREFERENCES_NAME, userForJson.getName());
                    editor.putString(APP_PREFERENCES_AGE, userForJson.getDate());
                    editor.putString(APP_PREFERENCES_CITY, userForJson.getCity());
                    editor.putString(APP_PREFERENCES_LOGIN, userForJson.getLogin());
                    editor.putString(APP_PREFERENCES_PASSWORD, userForJson.getPassword());
                    editor.putString(APP_PREFERENCES_MAIL, userForJson.getEmail());
                    editor.putString(APP_PREFERENCES_ID, userForJson.getUserId());
                    editor.putString(APP_PREFERENCES_FAVOURITE,userForJson.getFavourite());
                    editor.putString(APP_PREFERENCES_INTERVIEW,userForJson.getInterview());
                    editor.putString(APP_PREFERENCES_QUOTE,userForJson.getQuote());
                    editor.putString(APP_PREFERENCES_RECOMMEND_AUTHORS,userForJson.getRecommendAuthors());
                    editor.putString(APP_PREFERENCES_RECOMMEND_GENRES,userForJson.getRecommendGenres());
                    editor.putString(APP_PREFERENCES_SEX,userForJson.getSex());
                    editor.putString(APP_PREFERENCES_AVATAR, userForJson.getAvatar());
                    editor.apply();
                    Intent questionIntent = new Intent(Enterance.this,
                            MainActivity.class);
                    startActivityForResult(questionIntent, CHOOSE_THIEF);
                    overridePendingTransition(R.anim.down_anim, R.anim.alpha_to_zero);
                    a++;

            }catch (Exception e){
                Log.d("1",e.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(a == 0){

                rel.setVisibility(View.GONE);
            }
            super.onPostExecute(s);
        }
    }


    /*@Override
    protected void onStop() {
        super.onStop();
    }*/
}