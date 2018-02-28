package br.com.rotacilio.githubclient.request.modelService;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rodrigo on 28/02/18.
 */
public class RepositoryServiceModel {

    @SerializedName("items")
    private List<RepositoryServiceItemsModel> repositoryServiceItemsModelList;

    public RepositoryServiceModel(List<RepositoryServiceItemsModel> repositoryServiceItemsModelList) {
        this.repositoryServiceItemsModelList = repositoryServiceItemsModelList;
    }

    public RepositoryServiceModel() {
    }

    public List<RepositoryServiceItemsModel> getRepositoryServiceItemsModelList() {
        return repositoryServiceItemsModelList;
    }

    public void setRepositoryServiceItemsModelList(List<RepositoryServiceItemsModel> repositoryServiceItemsModelList) {
        this.repositoryServiceItemsModelList = repositoryServiceItemsModelList;
    }
}
