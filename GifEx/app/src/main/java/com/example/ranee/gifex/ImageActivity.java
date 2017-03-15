package com.example.ranee.gifex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }

    public void onBtn4Clicked(View v) {
        Toast.makeText(this, "Back to Menu", Toast.LENGTH_LONG).show();
        finish();
    }
}
