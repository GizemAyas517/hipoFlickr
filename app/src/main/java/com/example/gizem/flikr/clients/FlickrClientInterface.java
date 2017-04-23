package com.example.gizem.flikr.clients;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gizem on 4/23/2017.
 */

public interface FlickrClientInterface {
    @GET("https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=188843a1a5f0b6cd12b9346dc0f9cc81&per_page=10&page=1&format=json&nojsoncallback=1")
    Call<GetRecentPhotosResponse> photosForUser(

            );
}
