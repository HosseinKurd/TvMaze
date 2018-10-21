
package com.hosseinkurd.tvmaze.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links implements Parcelable {

    @SerializedName("self")
    @Expose
    private Self self;
    @SerializedName("previousEpisode")
    @Expose
    private PreviousEpisode previousEpisode;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public PreviousEpisode getPreviousEpisode() {
        return previousEpisode;
    }

    public void setPreviousEpisode(PreviousEpisode previousEpisode) {
        this.previousEpisode = previousEpisode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.self, flags);
        dest.writeParcelable(this.previousEpisode, flags);
    }

    public Links() {
    }

    protected Links(Parcel in) {
        this.self = in.readParcelable(Self.class.getClassLoader());
        this.previousEpisode = in.readParcelable(PreviousEpisode.class.getClassLoader());
    }

    public static final Parcelable.Creator<Links> CREATOR = new Parcelable.Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel source) {
            return new Links(source);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };
}
