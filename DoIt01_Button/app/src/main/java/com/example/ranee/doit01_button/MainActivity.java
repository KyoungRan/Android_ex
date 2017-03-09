package com.example.ranee.doit01_button;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(this, "버튼이 눌렸어요", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onButton2Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"));
        startActivity(intent);
    }

    public void onButton3Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000"));
        startActivity(intent);
    }
}
