package com.example.ranee.gifex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onBtn1Clicked(View v) {
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }

    public void onBtn2Clicked(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onBtn3Clicked(View v) {
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }
}
