package com.example.gizem.flikr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        PhotoView im= (PhotoView) findViewById(R.id.myImage);
        Intent intent=getIntent();
        String url=intent.getStringExtra("src");
        Picasso.with(this).load(url).into(im);
    }
}
