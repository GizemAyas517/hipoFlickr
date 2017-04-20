package com.example.gizem.flikr;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    String[] images = {
            "https://www.looktheapp.com/api/pictures/pic-1.jpg",
            "https://www.looktheapp.com/api/pictures/pic-2.jpeg",
            "https://www.looktheapp.com/api/pictures/pic-3.jpeg",
            "https://www.looktheapp.com/api/pictures/pic-3.jpg",
            "https://www.looktheapp.com/api/pictures/pic-4.jpeg",
            "https://www.looktheapp.com/api/pictures/pic-5.jpg",
            "https://www.looktheapp.com/api/pictures/pic-6.jpeg",
            "https://www.looktheapp.com/api/pictures/pic-7.jpeg",
            "https://www.looktheapp.com/api/pictures/pic-8.jpeg",
            "https://www.looktheapp.com/api/pictures/pic-9.jpeg",
            "https://www.looktheapp.com/api/pictures/pic-10.jpg",
            "https://www.looktheapp.com/api/pictures/pic-11.jpeg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout layout= (GridLayout) findViewById(R.id.myLayout);
        layout.setRowCount(images.length);
        layout.setColumnCount(2);

        for (String image : images) {
            ImageView im = new ImageView(this);
            Picasso.with(this).load(image).into(im);
            layout.addView(im);
        }

    }


}
