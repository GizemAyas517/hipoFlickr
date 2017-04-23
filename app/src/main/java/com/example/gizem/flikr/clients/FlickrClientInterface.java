package com.example.gizem.flikr.clients;

import java.util.List;

import retrofit2.Call;

/**
 * Created by gizem on 4/23/2017.
 */

public interface FlickrClientInterface {

    Call<GetRecentPhotosResponse> photosForUser(

            );
}
