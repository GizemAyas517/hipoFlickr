package com.example.gizem.flikr.models;

/**
 * Created by gizem on 4/23/2017.
 */

public class FlickrImage {
  public  String ID;
  public  String owner;
  public  String secret;
  public  String server;
  public int farm;
  public  String title;
  public  Boolean ispublic;
  public  Boolean isfriend;
  public  Boolean isfamily;

    public FlickrImage(String ID, String owner,String secret,String server,int farm, String title, Boolean ispublic, Boolean isfriend, Boolean isfamily ){
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



}
