package com.hosseinkurd.tvmaze.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieMdl implements Parcelable {

    public static final Parcelable.Creator<MovieMdl> CREATOR = new Parcelable.Creator<MovieMdl>() {
        @Override
        public MovieMdl createFromParcel(Parcel source) {
            return new MovieMdl(source);
        }

        @Override
        public MovieMdl[] newArray(int size) {
            return new MovieMdl[size];
        }
    };
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("premiered")
    @Expose
    private String premiered;
    @SerializedName("officialSite")
    @Expose
    private String officialSite;
    @SerializedName("schedule")
    @Expose
    private Schedule schedule;
    @SerializedName("rating")
    @Expose
    private Rating rating;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("network")
    @Expose
    private Network network;
    /*@SerializedName("webChannel")
    @Expose
    private String webChannel;*/
    @SerializedName("externals")
    @Expose
    private Externals externals;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("updated")
    @Expose
    private Integer updated;
    @SerializedName("_links")
    @Expose
    private Links links;

    public MovieMdl() {

    }

    protected MovieMdl(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.url = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.language = in.readString();
        this.genres = in.createStringArrayList();
        this.status = in.readString();
        this.runtime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.premiered = in.readString();
        this.officialSite = in.readString();
        this.schedule = in.readParcelable(Schedule.class.getClassLoader());
        this.rating = in.readParcelable(Rating.class.getClassLoader());
        this.weight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.network = in.readParcelable(Network.class.getClassLoader());
        // this.webChannel = in.readString();
        this.externals = in.readParcelable(Externals.class.getClassLoader());
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.summary = in.readString();
        this.updated = (Integer) in.readValue(Integer.class.getClassLoader());
        this.links = in.readParcelable(Links.class.getClassLoader());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    /*public Object getWebChannel() {
        return webChannel;
    }

    public void setWebChannel(String webChannel) {
        this.webChannel = webChannel;
    }*/

    public Externals getExternals() {
        return externals;
    }

    public void setExternals(Externals externals) {
        this.externals = externals;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.url);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.language);
        dest.writeStringList(this.genres);
        dest.writeString(this.status);
        dest.writeValue(this.runtime);
        dest.writeString(this.premiered);
        dest.writeString(this.officialSite);
        dest.writeParcelable(this.schedule, flags);
        dest.writeParcelable(this.rating, flags);
        dest.writeValue(this.weight);
        dest.writeParcelable(this.network, flags);
        // dest.writeString(this.webChannel);
        dest.writeParcelable(this.externals, flags);
        dest.writeParcelable(this.image, flags);
        dest.writeString(this.summary);
        dest.writeValue(this.updated);
        dest.writeParcelable(this.links, flags);
    }
}