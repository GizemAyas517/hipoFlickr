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


    public List<FlickrImage> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridLayout layout= (GridLayout) findViewById(R.id.myLayout);
        layout.setRowCount(10);
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
                GetRecentPhotosResponse photosResponse = response.body();
                images = photosResponse.photos.photo;

                for (final FlickrImage image : images) {
                    ImageButton im = new ImageButton(MainActivity.this);
                    im.setOnClickListener(new View.OnClickListener() {
                        String url = image.url();

                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this, ImageDetail.class);
                            intent.putExtra("src", url);
                            startActivity(intent);
                        }
                    });

                    Picasso.with(MainActivity.this).load(image.url()).resize(400, 400).into(im);
                    layout.addView(im);


                }
            }

            @Override
            public void onFailure(Call<GetRecentPhotosResponse> call, Throwable t) {
                try{
                    throw t;
                }catch(Throwable t1){
                    t1.printStackTrace();
                }

            }
        });




    }




}
