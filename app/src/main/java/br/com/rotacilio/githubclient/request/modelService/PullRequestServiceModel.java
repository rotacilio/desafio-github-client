package br.com.rotacilio.githubclient.request.modelService;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rodrigo on 28/02/18.
 */
public class PullRequestServiceModel {

    private String title;
    private String body;
    private String created_at;
    private String html_url;

    @SerializedName("user")
    private UserServiceModel userServiceModel;

    public PullRequestServiceModel() {
    }

    public PullRequestServiceModel(String title, String body, String created_at, String html_url, UserServiceModel userServiceModel) {
        this.title = title;
        this.body = body;
        this.created_at = created_at;
        this.html_url = html_url;
        this.userServiceModel = userServiceModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public UserServiceModel getUserServiceModel() {
        return userServiceModel;
    }

    public void setUserServiceModel(UserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }
}
