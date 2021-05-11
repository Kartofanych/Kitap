package com.example.irekonelove4;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;

public class Registration extends Activity {

    public static void buttonEffect(View button){
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




    static final private int CHOOSE_THIEF = 0;
    public SharedPreferences mSettings;
    private Calendar mcalendar;
    private EditText date, name, login, city, mail, password;
    private int day,month,year;
    private RadioButton man, woman;
    private Button regbut;
    private int kol = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        mcalendar = Calendar.getInstance();
        date = (EditText)findViewById(R.id.dateed);
        date.setOnClickListener(mClickListener);
        day = mcalendar.get(Calendar.DAY_OF_MONTH);
        year = mcalendar.get(Calendar.YEAR);
        month = mcalendar.get(Calendar.MONTH);
        regbut = (Button) findViewById(R.id.entbut);

        name = (EditText)findViewById(R.id.name);
        login = (EditText)findViewById(R.id.login);
        city = (EditText)findViewById(R.id.city);
        mail = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kol++;
                if(man.isChecked() && kol % 2 == 0){
                    man.setChecked(false);
                }
                if(woman.isChecked()){
                    kol = 1;
                    woman.setChecked(false);
                    man.setChecked(true);
                }                                                       ///////////////////////ШОБ РАДИО БАТТТОН БЫЛ НОРМАЛЬНЫМ///////////////////////////////////////
            }
        });
        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kol++;
                if(woman.isChecked() && kol % 2 == 0){
                    woman.setChecked(false);
                }
                if(man.isChecked()){
                    kol = 1;
                    man.setChecked(false);
                    woman.setChecked(true);
                }
            }
        });


        regbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES_NAME, name.getText().toString());
                editor.putString(APP_PREFERENCES_LOGIN, login.getText().toString());
                editor.putString(APP_PREFERENCES_CITY, city.getText().toString());
                editor.putString(APP_PREFERENCES_MAIL, mail.getText().toString());
                editor.putString(APP_PREFERENCES_PASSWORD, password.getText().toString());
                editor.putString(APP_PREFERENCES_AGE, date.getText().toString());
                editor.apply();

                Intent questionIntent = new Intent(Registration.this,
                        MainActivity.class);
                startActivityForResult(questionIntent, CHOOSE_THIEF);
                overridePendingTransition(R.anim.down_anim, R.anim.alpha_to_zero);
            }
        });

        buttonEffect(regbut);

    }

    View.OnClickListener mClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateDialog();
        }
    };

    public void DateDialog(){
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {          //////////////////////////ОКОШКА С ДАТОЙ
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                String itogMonth = String.valueOf(monthOfYear+1);
                if(monthOfYear + 1 < 10){
                    itogMonth = "0" + itogMonth;
                }
                String itogDays = String.valueOf(dayOfMonth);
                if(dayOfMonth < 10){
                    itogDays = "0" + itogDays;
                }
                date.setText(itogDays + "/" + itogMonth + "/" + year);
            }};
        DatePickerDialog dpDialog=new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent questionIntent = new Intent(this,
                Enterance.class);
        startActivityForResult(questionIntent, CHOOSE_THIEF);
        overridePendingTransition(R.anim.left_anim, R.anim.alpha_to_zero);
    }


}
