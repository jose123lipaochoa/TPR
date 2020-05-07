package com.example.tpr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {

    private static int SPLASH_SCREEN=5000;

    Animation topAnim,bottomAnim;
    ImageView img;
    TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inicio);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        img=(ImageView)findViewById(R.id.imageView);
        titulo=(TextView)findViewById(R.id.textView);
        img.setAnimation(topAnim);
        titulo.setAnimation(bottomAnim);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent= new Intent(Inicio.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
