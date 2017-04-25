package com.example.gizem.flikr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageDetail extends AppCompatActivity {

    PhotoViewAttacher mAttacher;
    ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        RelativeLayout rl= (RelativeLayout) findViewById(R.id.activity_image_detail);
        im= new ImageView(this);

        Callback imageLoadedCallback= new Callback(){

            public void onSuccess(){
                if(mAttacher!=null){
                    mAttacher.update();
                }else{
                    mAttacher= new PhotoViewAttacher(im);
                }

            }

            public void onError(){

            }

        };

        Intent intent=getIntent();
        String url=intent.getStringExtra("src");
        PhotoView ph= (PhotoView) findViewById(R.id.myPhoto);
        Picasso.with(this).load(url).into(ph,imageLoadedCallback);




    }
}
