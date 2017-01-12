package com.codetoart.movieapp.model.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by vikas on 10-Jan-17.
 */

@Entity
public class Movie implements Parcelable {

    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    public static Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            Movie movie = new Movie();
            movie.posterPath = parcel.readString();
            movie.adult = (Boolean) parcel.readValue(getClass().getClassLoader());
            movie.overview = parcel.readString();
            movie.releaseDate = parcel.readString();
            movie.id = parcel.readString();
            movie.title = parcel.readString();
            movie.backdropPath = parcel.readString();
            movie.voteAverage = parcel.readFloat();
            return movie;
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[0];
        }
    };

    @Property
    @SerializedName("poster_path")
    private String posterPath;
    @Property
    @SerializedName("adult")
    private boolean adult;
    @Property
    @SerializedName("overview")
    private String overview;
    @Property
    @SerializedName("release_date")
    private String releaseDate;
    @Id
    @SerializedName("id")
    private String id;
    @Property
    @SerializedName("title")
    private String title;
    @Property
    @SerializedName("backdrop_path")
    private String backdropPath;
    @NotNull
    @SerializedName("vote_average")
    private Float voteAverage;

    public Movie() {
    }

    @Generated(hash = 1369440620)
    public Movie(String posterPath, boolean adult, String overview, String releaseDate, String id, String title, String backdropPath,
                 @NotNull Float voteAverage) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.id = id;
        this.title = title;
        this.backdropPath = backdropPath;
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return TMDB_IMAGE_PATH + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public boolean getAdult() {
        return this.adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public boolean isEmpty() {
        return this == null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.posterPath);
        parcel.writeValue(this.adult);
        parcel.writeString(this.overview);
        parcel.writeString(this.releaseDate);
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.backdropPath);
        parcel.writeFloat(this.voteAverage);
    }
}
