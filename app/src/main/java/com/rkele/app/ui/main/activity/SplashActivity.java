package com.rkele.app.ui.main.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.rkele.app.R;
import com.rkele.app.ui.login.activity.LoginActivity;
import com.rkele.app.utils.SPUtils;

public class SplashActivity extends AppCompatActivity {
    private  Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (!TextUtils.isEmpty(SPUtils.get(SplashActivity.this, SPUtils.TOKEN, "") + "")
                && !TextUtils.isEmpty(SPUtils.get(SplashActivity.this, SPUtils.MERID, "") + "")){
             intent = new Intent(this, MainActivity.class);

        }else{
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
