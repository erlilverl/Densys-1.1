package com.densysMobile.android.dengue_phi_client.UI;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.densysMobile.android.dengue_phi_client.Login;
import com.densysMobile.android.dengue_phi_client.R;
import com.densysMobile.android.dengue_phi_client.Util.Util;


public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);

        boolean networkConnected = Util.isNetworkConnected(this);
        if (!networkConnected) {
            Snackbar.with(this, null)
                    .type(Type.ERROR)
                    .message("No Internet Connection Available")
                    .duration(Duration.INFINITE)
                    .show();
        } else {
//            Snackbar.with(this, null)
//                    .type(Type.SUCCESS)
//                    .message("Network Available")
//                    .duration(Duration.INFINITE)
//                    .show();
        }
        runAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
                startActivity(intent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void runAnimation() {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        a.reset();
        TextView tv = (TextView) findViewById(R.id.splashText);
        tv.clearAnimation();
        tv.startAnimation(a);
    }
}
