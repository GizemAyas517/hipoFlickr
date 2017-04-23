package com.example.gizem.flikr.clients;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gizem on 4/23/2017.
 */

public class FlickrImageResponse {

    @SerializedName("photo")
    public List<FlickrImage> photo;


    @SerializedName("page")
    public int page;
    @SerializedName("pages")
    public int pages;
    @SerializedName("perpage")
    public int perpage;
    @SerializedName("total")
    public String total;

    public FlickrImageResponse(List<FlickrImage> photo,int page, int pages, int perpage, String total){
        this.photo=photo;
        this.page=page;
        this.pages=pages;
        this.perpage=perpage;
        this.total=total;
    }
}
