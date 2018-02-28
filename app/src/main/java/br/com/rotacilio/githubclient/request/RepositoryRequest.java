package br.com.rotacilio.githubclient.request;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.rotacilio.githubclient.common.CallbackRequest;
import br.com.rotacilio.githubclient.model.ItemList;
import br.com.rotacilio.githubclient.request.modelService.RepositoryServiceItemsModel;
import br.com.rotacilio.githubclient.request.modelService.RepositoryServiceModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rodrigo on 28/02/18.
 */
public class RepositoryRequest extends GitBaseRequest implements Callback<RepositoryServiceModel> {

    @Override
    public void onResponse(@NonNull Call<RepositoryServiceModel> call, @NonNull Response<RepositoryServiceModel> response) {
        List<ItemList> itemLists = new ArrayList<>();

        if(response.isSuccessful()){
            if(response.body() != null && response.body().getRepositoryServiceItemsModelList() != null){

                for (RepositoryServiceItemsModel repo:
                        response.body().getRepositoryServiceItemsModelList()) {

                    ItemList itemList = new ItemList();
                    itemList.setDescription(repo.getDescription());
                    itemList.setfork(repo.getForks_count());
                    itemList.setImgUrl(repo.getUserServiceModel().getAvatar_url());
                    itemList.setName(repo.getUserServiceModel().getUsername());
                    itemList.setStars(repo.getStargazers_count());
                    itemList.setTitle(repo.getName());
                    itemList.setFull_repository_name(repo.getFull_name());
                    itemLists.add(itemList);
                }
            }
        }

        this.callbackRequest.response(itemLists);
    }

    @Override
    public void onFailure(@NonNull Call<RepositoryServiceModel> call, @NonNull Throwable t) {
        this.callbackRequest.failure();
    }

    public void call(String page, CallbackRequest callbackRequest, Context context) {
        super.call(callbackRequest, context);

        Call<RepositoryServiceModel> call = this.gitServices.getRepositorys(page);
        call.enqueue(this);
    }
}
