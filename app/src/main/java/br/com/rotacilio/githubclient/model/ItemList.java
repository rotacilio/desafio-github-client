package br.com.rotacilio.githubclient.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rodrigo on 28/02/18.
 */
public class ItemList implements Parcelable {
    private String title;
    private String full_repository_name;
    private String description;
    private String imgUrl;
    private String nameUser;
    private String name;
    private long stars;
    private long fork;
    private String urlPullRequest;
    private String datePullRequest;

    public ItemList() {
    }

    public String getFull_repository_name() {
        return full_repository_name;
    }

    public void setFull_repository_name(String full_repository_name) {
        this.full_repository_name = full_repository_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStars() {
        return stars;
    }

    public void setStars(long stars) {
        this.stars = stars;
    }

    public long getfork() {
        return fork;
    }

    public void setfork(long fork) {
        this.fork = fork;
    }

    public String getUrlPullRequest() {
        return urlPullRequest;
    }

    public void setUrlPullRequest(String urlPullRequest) {
        this.urlPullRequest = urlPullRequest;
    }

    public String getDatePullRequest() {
        return datePullRequest;
    }

    public void setDatePullRequest(String datePullRequest) {
        this.datePullRequest = datePullRequest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(full_repository_name);
        dest.writeString(description);
        dest.writeString(imgUrl);
        dest.writeString(nameUser);
        dest.writeString(name);
        dest.writeLong(stars);
        dest.writeLong(fork);
        dest.writeString(urlPullRequest);
        dest.writeString(datePullRequest);
    }

    protected ItemList(Parcel in) {
        title = in.readString();
        full_repository_name = in.readString();
        description = in.readString();
        imgUrl = in.readString();
        nameUser = in.readString();
        name = in.readString();
        stars = in.readLong();
        fork = in.readLong();
        urlPullRequest = in.readString();
        datePullRequest = in.readString();
    }

    public static final Creator<ItemList> CREATOR = new Creator<ItemList>() {
        @Override
        public ItemList createFromParcel(Parcel in) {
            return new ItemList(in);
        }

        @Override
        public ItemList[] newArray(int size) {
            return new ItemList[size];
        }
    };
}
