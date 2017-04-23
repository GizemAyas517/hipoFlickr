package com.example.gizem.flikr;


import android.content.Intent;
import android.content.SyncStatusObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;



import com.example.gizem.flikr.clients.FlickrClientInterface;
import com.example.gizem.flikr.clients.FlickrImage;
import com.example.gizem.flikr.clients.GetRecentPhotosResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
       final GridLayout layout= (GridLayout) findViewById(R.id.myLayout);
        layout.setRowCount(images.length);
        layout.setColumnCount(2);

        String API_BASE_URL= "https://api.flickr.com/services/rest/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder= new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(
                        GsonConverterFactory.create()
                );
        Retrofit retrofit= builder.client(httpClient.build()).build();
        FlickrClientInterface client=retrofit.create(FlickrClientInterface.class);

        Call<GetRecentPhotosResponse> call= client.photosForUser();

        call.enqueue(new Callback<GetRecentPhotosResponse>() {
            @Override
            public void onResponse(Call<GetRecentPhotosResponse> call, Response<GetRecentPhotosResponse> response) {
                GetRecentPhotosResponse photosResponse=response.body();
                List<FlickrImage>images= (List<FlickrImage>) photosResponse.photos;
                //get the urls
                for(FlickrImage image: images){
                    ImageButton im = new ImageButton(MainActivity.this);
                    im.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View view){
                            Intent intent= new Intent(MainActivity.this,ImageDetail.class);
                            intent.putExtra("src",clicked);
                            startActivity(intent);
                        }
                    });

                    Picasso.with(MainActivity.this).load(image.url()).resize(400,400).into(im);
                    layout.addView(im);

                }
            }

            @Override
            public void onFailure(Call<GetRecentPhotosResponse> call, Throwable t) {

            }
        });


    }




}
