package br.com.rotacilio.githubclient.request.modelService;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rodrigo on 28/02/18.
 */
public class UserServiceModel {

    private String avatar_url;

    @SerializedName("login")
    private String username;

    public UserServiceModel() {
    }

    public UserServiceModel(String avatar_url, String username) {
        this.avatar_url = avatar_url;
        this.username = username;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
