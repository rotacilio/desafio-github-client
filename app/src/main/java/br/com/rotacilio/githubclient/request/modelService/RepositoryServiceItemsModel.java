package br.com.rotacilio.githubclient.request.modelService;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rodrigo on 28/02/18.
 */
public class RepositoryServiceItemsModel {

    @SerializedName("owner")
    private UserServiceModel userServiceModel;

    private String name;
    private String full_name;
    private String description;
    private long forks_count;
    private long stargazers_count;

    public RepositoryServiceItemsModel() {
    }

    public RepositoryServiceItemsModel(UserServiceModel userServiceModel, String name, String full_name, String description, long forks_count, long stargazers_count) {
        this.userServiceModel = userServiceModel;
        this.name = name;
        this.full_name = full_name;
        this.description = description;
        this.forks_count = forks_count;
        this.stargazers_count = stargazers_count;
    }

    public UserServiceModel getUserServiceModel() {
        return userServiceModel;
    }

    public void setUserServiceModel(UserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getForks_count() {
        return forks_count;
    }

    public void setForks_count(long forks_count) {
        this.forks_count = forks_count;
    }

    public long getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(long stargazers_count) {
        this.stargazers_count = stargazers_count;
    }
}
