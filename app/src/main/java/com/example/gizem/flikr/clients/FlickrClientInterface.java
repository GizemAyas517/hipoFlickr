package com.example.gizem.flikr.clients;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gizem on 4/23/2017.
 */

public interface FlickrClientInterface {
    @GET("https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&per_page=10&format=json&nojsoncallback=1")
    Call<GetRecentPhotosResponse> photosForUser(
            @Query("api_key") String api_key,
            @Query("page") Integer page

            );
    @GET("https://api.flickr.com/services/rest/?method=flickr.photos.search&per_page=10&text=dog&format=json&sort=relevance&json&nojsoncallback=1")
    Call<GetRecentPhotosResponse> searchResult(
            @Query("api_key") String api_key,
            @Query("text") String text,
            @Query("page") Integer page
    );

}
