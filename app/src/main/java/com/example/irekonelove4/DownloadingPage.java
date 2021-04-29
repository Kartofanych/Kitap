package com.example.irekonelove4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class DownloadingPage extends Activity {


    static final private int CHOOSE_THIEF = 0;
    int ch = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downloading_page);
        if(ch == 0) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent questionIntent = new Intent(DownloadingPage.this,
                            MainActivity.class);
                    startActivityForResult(questionIntent, CHOOSE_THIEF);
                    overridePendingTransition(R.anim.down_anim, R.anim.alpha_to_zero);
                    finish();
                    ch++;
                }
            }, 1500);
        }
    }
}
