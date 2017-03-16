package com.example.ranee.gifex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ImageActivity extends AppCompatActivity {

    // constant
    private static final int INTENT_REQUEST_GET_IMAGES = 8;

    ArrayList<Uri> image_uris = new ArrayList<Uri>();
    private ViewGroup mSelectedImagesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mSelectedImagesContainer = (ViewGroup) findViewById(R.id.selected_photos_container);
        View getImages = findViewById(R.id.get_images);
        getImages.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Config config = new Config();

                getImages(config);
            }
        });
    }

    private void getImages(Config config) {

        ImagePickerActivity.setConfig(config);

        Intent intent = new Intent(this, ImagePickerActivity.class);

        if (image_uris != null) {
            intent.putParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS, image_uris);
        }

        startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);


        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == INTENT_REQUEST_GET_IMAGES) {

                image_uris = intent.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);

                if (image_uris != null) {
                    showMedia();
                }

            }
        }
    }


    private void showMedia() {
        // Remove all views before
        // adding the new ones.
        mSelectedImagesContainer.removeAllViews();
        if (image_uris.size() >= 1) {
            mSelectedImagesContainer.setVisibility(View.VISIBLE);
        }

        int wdpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int htpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());


        for (Uri uri : image_uris) {

            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
            ImageView thumbnail = (ImageView) imageHolder.findViewById(R.id.media_image);

            Glide.with(this)
                    .load(uri.toString())
                    .fitCenter()
                    .into(thumbnail);

            mSelectedImagesContainer.addView(imageHolder);

            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(wdpx, htpx));

        }

    }

    public void onConvertGif(View v) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.setDelay(500);
        encoder.setRepeat(0);
        encoder.start(bos);

            Bitmap bmp1, bmp2, bmp3;

            bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.images1);
            encoder.addFrame(bmp1);
            //bmp1.recycle();

            bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.images2);
            encoder.addFrame(bmp2);
            //bmp2.recycle();

            bmp3= BitmapFactory.decodeResource(getResources(), R.drawable.images3);
            encoder.addFrame(bmp3);
            //bmp3.recycle();

        encoder.finish();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File imagesFolder = new File(extStorageDirectory, "Rolling");
        imagesFolder.mkdir();

        File filePath = new File(imagesFolder, "rolling_" + timeStamp + ".GIF");
        //File filePath = new File("/sdcard", "sample.gif");

        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(filePath);

            outputStream.write(bos.toByteArray());

            Toast.makeText(this, filePath.getAbsolutePath() + " Saved", Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void onBtn4Clicked(View v) {
        Toast.makeText(this, "Back to Menu", Toast.LENGTH_LONG).show();
        finish();
    }
}
