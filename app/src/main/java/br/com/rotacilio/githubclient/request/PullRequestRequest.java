package br.com.rotacilio.githubclient.request;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.rotacilio.githubclient.common.CallbackRequest;
import br.com.rotacilio.githubclient.model.ItemList;
import br.com.rotacilio.githubclient.request.modelService.PullRequestServiceModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rodrigo on 28/02/18.
 */
@SuppressLint("SimpleDateFormat")
public class PullRequestRequest extends GitBaseRequest implements Callback<List<PullRequestServiceModel>> {

    @Override
    public void onResponse(@NonNull Call<List<PullRequestServiceModel>> call, @NonNull Response<List<PullRequestServiceModel>> response) {
        List<ItemList> itemLists = new ArrayList<>();
        if(response.isSuccessful()){
            if(response.body() != null && response.body().size() > 0){

                for (PullRequestServiceModel pull:
                     response.body()) {

                    try{

                        ItemList itemList = new ItemList();
                        itemList.setDescription(pull.getBody());
                        itemList.setfork(-0);
                        itemList.setStars(-0);
                        itemList.setImgUrl(pull.getUserServiceModel().getAvatar_url());
                        itemList.setName(pull.getUserServiceModel().getUsername());
                        itemList.setTitle(pull.getTitle());


                        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        Date date = dt.parse(pull.getCreated_at());
                        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-mm-dd");

                        itemList.setDatePullRequest(dt1.format(date));
                        itemList.setUrlPullRequest(pull.getHtml_url());
                        itemLists.add(itemList);

                    } catch (Exception e){

                    }
                }

            }
        }

        this.callbackRequest.response(itemLists);
    }

    @Override
    public void onFailure(@NonNull Call<List<PullRequestServiceModel>> call, @NonNull Throwable t) {
        this.callbackRequest.failure();

    }

    public void call(String creator, String repository, CallbackRequest callbackRequest, Context context) {
        super.call(callbackRequest, context);

        Call<List<PullRequestServiceModel>> call = gitServices.getPullRequestList(creator, repository);
        call.enqueue(this);
    }
}
