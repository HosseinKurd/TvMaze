
package com.hosseinkurd.tvmaze.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreviousEpisode implements Parcelable {

    @SerializedName("href")
    @Expose
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.href);
    }

    public PreviousEpisode() {
    }

    protected PreviousEpisode(Parcel in) {
        this.href = in.readString();
    }

    public static final Parcelable.Creator<PreviousEpisode> CREATOR = new Parcelable.Creator<PreviousEpisode>() {
        @Override
        public PreviousEpisode createFromParcel(Parcel source) {
            return new PreviousEpisode(source);
        }

        @Override
        public PreviousEpisode[] newArray(int size) {
            return new PreviousEpisode[size];
        }
    };
}
