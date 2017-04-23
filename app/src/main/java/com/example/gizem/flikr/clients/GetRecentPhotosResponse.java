package com.example.gizem.flikr.clients;

import com.example.gizem.flikr.clients.FlickrImageResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gizem on 4/23/2017.
 */

public class GetRecentPhotosResponse {
    @SerializedName("photos")
    public FlickrImageResponse photos;

    @SerializedName("stat")
    public String stat;

}
