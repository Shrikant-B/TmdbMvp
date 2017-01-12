package com.codetoart.movieapp.model.image;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikas on 10-Jan-17.
 */

public class Image {

    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    @SerializedName("aspect_ratio")
    String aspectRatio;
    @SerializedName("file_path")
    String filePath;
    @SerializedName("height")
    int height;
    @SerializedName("vote_average")
    float voteAverage;
    @SerializedName("vote_count")
    float voteCount;
    @SerializedName("width")
    int width;

    public Image() {
    }

    public Image(String aspectRatio, String filePath, int height, float voteAverage, float voteCount, int width) {
        this.aspectRatio = aspectRatio;
        this.filePath = filePath;
        this.height = height;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.width = width;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return TMDB_IMAGE_PATH + filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public float getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(float voteCount) {
        this.voteCount = voteCount;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
