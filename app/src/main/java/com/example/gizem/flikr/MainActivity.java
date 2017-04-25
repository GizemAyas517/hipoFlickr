package com.example.gizem.flikr;


import android.content.Intent;
import android.content.SyncStatusObserver;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Toast;



import com.example.gizem.flikr.clients.FlickrClientInterface;
import com.example.gizem.flikr.clients.FlickrImage;
import com.example.gizem.flikr.clients.GetRecentPhotosResponse;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public List<FlickrImage> images=new ArrayList<FlickrImage>();
    public ArrayList<ImageButton> oldImages;

    public ArrayList<ImageButton> removable= new ArrayList<>();
    MaterialSearchView searchView;
    int page=1;
    Toolbar tb;
    String text="";
    GridLayout layout;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout= (GridLayout) findViewById(R.id.myLayout);
        layout.setRowCount(10);
        layout.setColumnCount(2);

        scrollView= (ScrollView) findViewById(R.id.scrollView);



        tb= (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Flickr");
        tb.setTitleTextColor(Color.parseColor("#FFFFFF"));

        searchView= (MaterialSearchView) findViewById(R.id.searchView);
        searchView.setTextColor(Color.parseColor("#FFFFFF"));

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener(){


            @Override
            public boolean onQueryTextSubmit(String query) {



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                text=newText;
                searchResult();
                return false;
            }
        });



        searchResult();
    }


    public void onScrollChanged(ScrollViewExt scrollView, int x, int y, int oldx, int oldy) {
        // We take the last son in the scrollview
        View view = (View) scrollView.getChildAt(scrollView.getChildCount() - 1);
        int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

        // if diff is zero, then the bottom has been reached
        if (diff < 10) {
            page++;

            searchResult();
        }

    }

    public void searchResult(){

        if(!text.isEmpty()){
            for(ImageButton img: oldImages ){
                layout.removeView(img);
            }
        }
        oldImages=new ArrayList<>();

        for(ImageButton im: removable){
            layout.removeView(im);
        }
        removable= new ArrayList<>();



        String API_BASE_URL= "https://api.flickr.com/services/rest/";
        String API_KEY = "97472a1d0e284013bb9b575b9205d61a";
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder= new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(
                        GsonConverterFactory.create()
                );
        Retrofit retrofit= builder.client(httpClient.build()).build();
        FlickrClientInterface client=retrofit.create(FlickrClientInterface.class);
        Call<GetRecentPhotosResponse> call;

        if(text.isEmpty()){
            call = client.photosForUser(API_KEY,page);
        } else {
            call= client.searchResult(API_KEY,text);
        }

        call.enqueue(new Callback<GetRecentPhotosResponse>() {
            @Override
            public void onResponse(Call<GetRecentPhotosResponse> call, Response<GetRecentPhotosResponse> response) {
                GetRecentPhotosResponse photosResponse = response.body();

                images = photosResponse.photos.photo;

                for (final FlickrImage image : images) {
                    Log.i("RESPONSE",image.url());
                    ImageButton im = new ImageButton(MainActivity.this);
                    removable.add(im);
                    oldImages.add(im);
                    im.setOnClickListener(new View.OnClickListener() {
                        String url = image.url();
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this, ImageDetail.class);
                            intent.putExtra("src", url);
                            startActivity(intent);
                        }
                    });
                    Picasso.with(MainActivity.this).load(image.url()).resize(450, 450).into(im);
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

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item= menu.findItem(R.id.app_bar_search);
        searchView.setMenuItem(item);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        if(id==R.id.app_bar_search){
            //do stuff here
            return true;
        }
        return true;
    }




}
