package com.example.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.demo.R;
import com.example.demo.util.SPHelper;

/**
 * Splash screen
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        int random = 0;//new Random().nextInt(10);
        findViewById(R.id.img).setBackgroundResource(R.mipmap.ic_launcher + random);

        // 2s
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SPHelper spHelper = SPHelper.getInstance(SplashActivity.this);
                            if (spHelper.getUserId() != 0 && spHelper.getRemeber() == 1) {
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            } else {
                                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                finish();
                            }
                        }
                    });
                }
            }
        }).start();
    }

}
