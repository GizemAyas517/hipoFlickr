package com.example.gizem.flikr.models;

/**
 * Created by gizem on 4/23/2017.
 */

public class FlickrImageList {
    public int page, pages, perpage;
    public String total;

    public FlickrImageList(int page, int pages, int perpage, String total){
        this.page=page;
        this.pages=pages;
        this.perpage=perpage;
        this.total=total;
    }
}
