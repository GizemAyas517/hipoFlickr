package com.example.gizem.flikr.clients;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gizem on 4/23/2017.
 */

public class FlickrImage {

  @SerializedName("id")
  public  String ID;
    @SerializedName("owner")
  public  String owner;
    @SerializedName("secret")
  public  String secret;
    @SerializedName("server")
  public  String server;
    @SerializedName("farm")
  public int farm;
    @SerializedName("title")
  public  String title;
    @SerializedName("ispublic")
  public  int ispublic;
    @SerializedName("isfriend")
  public  int isfriend;
    @SerializedName("isfamily")
  public  int isfamily;

    public FlickrImage(String ID, String owner,String secret,String server,int farm, String title, int ispublic, int isfriend, int isfamily ){
        this.ID=ID;
        this.owner=owner;
        this.secret=secret;
        this.server=server;
        this.farm=farm;
        this.title=title;
        this.ispublic=ispublic;
        this.isfamily=isfamily;
        this.isfriend=isfriend;
    }


    public String url(){
        return String.format("https://farm%d.staticflickr.com/%s/%s_%s.jpg",farm,server,ID,secret);
    }


}
