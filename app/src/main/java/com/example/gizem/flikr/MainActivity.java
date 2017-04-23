package com.example.gizem.flikr;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


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
    public String clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout layout= (GridLayout) findViewById(R.id.myLayout);
        layout.setRowCount(images.length);
        layout.setColumnCount(2);


        for (String image : images) {
            clicked=image;
            ImageButton im = new ImageButton(this);
         im.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View view){
                 Intent intent= new Intent(MainActivity.this,ImageDetail.class);
                 String text=clicked;
                 Toast toast= Toast.makeText(MainActivity.this,text, Toast.LENGTH_SHORT);
                 toast.show();
                 intent.putExtra("src",clicked);
                 startActivity(intent);
             }
         });

            Picasso.with(this).load(image).resize(400,400).into(im);
            layout.addView(im);
            clicked="";
        }

    }




}
