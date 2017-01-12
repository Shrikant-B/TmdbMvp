package com.codetoart.movieapp.model.image;

import java.util.ArrayList;

/**
 * Created by vikas on 10-Jan-17.
 */

public class ImageResponse {

    int no;
    String id;
    ArrayList<Image> backdrops;
    ArrayList<Image> posters;

    public  ImageResponse(){}

    public ImageResponse(int no, String id, ArrayList<Image> backdrops, ArrayList<Image> posters) {
        this.no = no;
        this.id = id;
        this.backdrops = backdrops;
        this.posters = posters;
    }

    public int getNo() {
        return posters.size();
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Image> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<Image> backdrops) {
        this.backdrops = backdrops;
    }

    public ArrayList<Image> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<Image> posters) {
        this.posters = posters;
    }
}
