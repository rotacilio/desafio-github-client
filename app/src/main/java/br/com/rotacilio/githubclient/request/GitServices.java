package br.com.rotacilio.githubclient.request;

import java.util.List;

import br.com.rotacilio.githubclient.request.modelService.PullRequestServiceModel;
import br.com.rotacilio.githubclient.request.modelService.RepositoryServiceModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rodrigo on 28/02/18.
 */
public interface GitServices {

    @GET("search/repositories?q=language:Java&sort=stars")
    Call<RepositoryServiceModel> getRepositorys(@Query("page") String page);

    @GET("repos/{creator}/{repo}/pulls")
    Call<List<PullRequestServiceModel>> getPullRequestList(@Path("creator") String creator, @Path("repo") String repo);
}
