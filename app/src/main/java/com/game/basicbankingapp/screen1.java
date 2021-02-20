package com.game.basicbankingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class screen1 extends Activity {

    TextView designed, name, app_name,app_name1,i_view2;
    ImageView logo, i_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);

        logo = findViewById(R.id.logo);
        designed = findViewById(R.id.designed);
        name = findViewById(R.id.name);
        app_name = findViewById(R.id.app_name);
        app_name1 = findViewById(R.id.app_name2);
        i_view = findViewById(R.id.i_view);
        i_view2 = findViewById(R.id.i_view2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startEnterAnimation();
            }
        }, 2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        }, 5500);
    }

    private void startEnterAnimation() {
        i_view.startAnimation(AnimationUtils.loadAnimation(screen1.this, R.anim.top));
        i_view2.startAnimation(AnimationUtils.loadAnimation(screen1.this, R.anim.fade));
        app_name.startAnimation(AnimationUtils.loadAnimation(screen1.this, R.anim.bottom));
        app_name1.startAnimation(AnimationUtils.loadAnimation(screen1.this, R.anim.bottom));
        logo.startAnimation(AnimationUtils.loadAnimation(screen1.this, R.anim.bottom));

        logo.setVisibility(View.VISIBLE);
        designed.setVisibility(View.VISIBLE);
        name.setVisibility(View.VISIBLE);
        app_name.setVisibility(View.VISIBLE);
        app_name1.setVisibility(View.VISIBLE);
        i_view.setVisibility(View.VISIBLE);
        i_view2.setVisibility(View.VISIBLE);
    }
}
