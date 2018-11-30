package com.quickefi.retailapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.quickefi.retailapp.R;


public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnFb,btnGoogle,btnTwitter;
    private SplashActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context=this;
        assignViews();
        bindViews();
    }

    private void assignViews() {
        btnFb = findViewById(R.id.btnFb);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnTwitter = findViewById(R.id.btnTwitter);
    }

    private void bindViews() {
        btnFb.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        btnTwitter.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(context,MainActivity.class));
        finish();
    }
}
