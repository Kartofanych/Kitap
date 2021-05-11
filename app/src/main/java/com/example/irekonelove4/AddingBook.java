package com.example.irekonelove4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class AddingBook extends Activity {

    private View download;
    private Button send;
    private File file;


    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_CITY = "City";
    public static final String APP_PREFERENCES_MAIL = "Mail";
    public static final String APP_PREFERENCES_PASSWORD = "Pass";
    public static final String APP_PREFERENCES_SEX = "00";
    public static final String APP_PREFERENCES_AVATAR = "AVATAR";
    public static final String APP_PREFERENCES_ID = "ID";
    public static final String APP_PREFERENCES_QUOTE = "Quote";
    public static final String APP_PREFERENCES_FAVOURITE = "Fav";
    public static final String APP_PREFERENCES_RECOMMEND_GENRES = "GENRES";
    public static final String APP_PREFERENCES_RECOMMEND_AUTHORS = "AUTHORS";
    public static final String APP_PREFERENCES_INTERVIEW = "Interview";
    public static final String APP_PREFERENCES_DATA = "Data";
    SharedPreferences mSettings;
    public static final String APP_PREFS_NAME = SyncStateContract.Constants.class.getPackage().getName();
    public static final String APP_CACHE_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/Android/data/" + APP_PREFS_NAME + "/cache/";


    private final int Pick_file = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_book);
        mSettings = this.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        download = findViewById(R.id.niz);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browseDocuments();
            }
        });
        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:kartofanych@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, file);
                intent.putExtra(Intent.EXTRA_TEXT, mSettings.getString(APP_PREFERENCES_NAME,""));
                Log.d("1","nice");
                startActivity(intent);
                Log.d("1", String.valueOf(file));
                //browseDocuments();
            }
        });

    }

    private void browseDocuments(){

        String[] mimeTypes =
                {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                        "application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
                        "application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                        "text/plain",
                        "application/pdf",
                        "application/zip"};

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
            if (mimeTypes.length > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
        } else {
            String mimeTypesStr = "";
            for (String mimeType : mimeTypes) {
               mimeTypesStr += mimeType + "|";
            }
            intent.setType(mimeTypesStr.substring(0,mimeTypesStr.length() - 1));
        }
        startActivityForResult(Intent.createChooser(intent,"ChooseFile"), Pick_file);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        getWindow().getDecorView().setSystemUiVisibility(
                //View.SYSTEM_UI_FLAG_LAYOUT_STABLE |                                   //скрываем нижнюю панель с кнопками
                //View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |                    //скрываем нижнюю панель с кнопками
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |                         //скрываем нижнюю панель с кнопками
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |                           //скрываем нижнюю панель с кнопками
                        //View.SYSTEM_UI_FLAG_FULLSCREEN |                                //скрываем нижнюю панель с кнопками
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        //скрываем нижнюю панель с кнопками
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent fileReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, fileReturnedIntent);

        switch (requestCode) {
            case Pick_file:
                if (resultCode == RESULT_OK) {
                    try {

                        ////////////////////////////////Здесь мы должны залить фотку в бд и в след раз брать оттуда
                        //Получаем URI изображения, преобразуем его в Bitmap
                        //объект и отображаем в элементе ImageView нашего интерфейса:
                        final Uri uri = fileReturnedIntent.getData();

                        Log.d("0",uri.toString());

                        file = new File(uri.getPath());


                        //String selectedFilePath = file.getPath(, uri);
                        //final File file = new File(selectedFilePath);
                        //final InputStream fileStream = getApplication().getContentResolver().openInputStream(file);
                        //selectedFile = BitmapFactory.decodeStream(file);
                        //Log.d("0",file.toString());

//                        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
//
//                        emailIntent.setType("plain/text");
//                        // Кому
//                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
//                                new String[] { "kartofanych@gmail.com" });
//                        // Зачем
//                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
//                                mSettings.getString(APP_PREFERENCES_NAME, "")
//                                );
//                       // С чем
//                        emailIntent.putExtra(android.content.Intent.EXTRA_STREAM,
//                                //Uri.fromFile(new File(String.valueOf(file)))
//                                selectedFile);
//                        startActivity(emailIntent);
                        /*Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("plain/text");
                       emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "kartofanych@gmail.com");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "EMAIL_TEXT");
                        emailIntent.putExtra(android.content.Intent.EXTRA_STREAM, selectedFile);
                        startActivity(emailIntent);*/


                    } catch (Exception e) {
                       e.printStackTrace();
                   }

                }
        }
    }


}
