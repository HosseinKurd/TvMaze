package com.hosseinkurd.tvmaze.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Externals implements Parcelable {

    public static final Parcelable.Creator<Externals> CREATOR = new Parcelable.Creator<Externals>() {
        @Override
        public Externals createFromParcel(Parcel source) {
            return new Externals(source);
        }

        @Override
        public Externals[] newArray(int size) {
            return new Externals[size];
        }
    };
    @SerializedName("tvrage")
    @Expose
    private Integer tvrage;
    @SerializedName("thetvdb")
    @Expose
    private Integer thetvdb;
    @SerializedName("imdb")
    @Expose
    private String imdb;

    public Externals() {
    }

    protected Externals(Parcel in) {
        this.tvrage = (Integer) in.readValue(Integer.class.getClassLoader());
        this.thetvdb = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imdb = in.readString();
    }

    public Integer getTvrage() {
        return tvrage;
    }

    public void setTvrage(Integer tvrage) {
        this.tvrage = tvrage;
    }

    public Integer getThetvdb() {
        return thetvdb;
    }

    public void setThetvdb(Integer thetvdb) {
        this.thetvdb = thetvdb;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.tvrage);
        dest.writeValue(this.thetvdb);
        dest.writeString(this.imdb);
    }
}
